package rx.lang.kotlin

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction


fun BooleanArray.toFlowable(): Flowable<Boolean> = this.asIterable().toFlowable()
fun ByteArray.toFlowable(): Flowable<Byte> = this.asIterable().toFlowable()
fun ShortArray.toFlowable(): Flowable<Short> = this.asIterable().toFlowable()
fun IntArray.toFlowable(): Flowable<Int> = this.asIterable().toFlowable()
fun LongArray.toFlowable(): Flowable<Long> = this.asIterable().toFlowable()
fun FloatArray.toFlowable(): Flowable<Float> = this.asIterable().toFlowable()
fun DoubleArray.toFlowable(): Flowable<Double> = this.asIterable().toFlowable()

fun IntProgression.toFlowable(): Flowable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Flowable.range(first, Math.max(0, last - first + 1))
        else Flowable.fromIterable(this)

fun <T : Any> Iterator<T>.toFlowable(): Flowable<T> = toIterable().toFlowable()
fun <T : Any> Iterable<T>.toFlowable(): Flowable<T> = Flowable.fromIterable(this)
fun <T : Any> Sequence<T>.toFlowable(): Flowable<T> = asIterable().toFlowable()

fun <T : Any> Iterable<Flowable<out T>>.merge(): Flowable<T> = Flowable.merge(this.toFlowable())
fun <T : Any> Iterable<Flowable<out T>>.mergeDelayError(): Flowable<T> = Flowable.mergeDelayError(this.toFlowable())

/**
 * Returns Flowable that wrap all values into [IndexedValue] and populates corresponding index value.
 * Works similar to [kotlin.withIndex]
 */
fun <T : Any> Flowable<T>.withIndex(): Flowable<IndexedValue<T>>
        = zipWith(Flowable.range(0, Int.MAX_VALUE), BiFunction { value, index -> IndexedValue(index, value) })

/**
 * Returns Flowable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one flowable.
 * Works similar to [Flowable.flatMap] and [Flowable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source flowable that returns [Sequence]
 * @returns Flowable that merges all [Sequence]s produced by [body] functions
 */
inline fun <T : Any, R : Any> Flowable<T>.flatMapSequence(crossinline body: (T) -> Sequence<R>): Flowable<R>
        = flatMap { body(it).toFlowable() }

fun <T : Any> Flowable<Flowable<T>>.switchOnNext(): Flowable<T> = Flowable.switchOnNext(this)

/**
 * Flowable.combineLatest(List<? extends Flowable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, R> Iterable<Flowable<T>>.combineLatest(crossinline combineFunction: (args: List<T>) -> R): Flowable<R>
        = Flowable.combineLatest(this) { combineFunction(it.asList().map { it as T }) }

/**
 * Flowable.zip(List<? extends Flowable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, R> Iterable<Flowable<T>>.zip(crossinline zipFunction: (args: List<T>) -> R): Flowable<R>
        = Flowable.zip(this) { zipFunction(it.asList().map { it as T }) }

/**
 * Returns an Flowable that emits the items emitted by the source Flowable, converted to the specified type.
 */
inline fun <reified R : Any> Flowable<*>.cast(): Flowable<R> = cast(R::class.java)

/**
 * Filters the items emitted by an Flowable, only emitting those of the specified type.
 */
inline fun <reified R : Any> Flowable<*>.ofType(): Flowable<R> = ofType(R::class.java)

private fun <T : Any> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}