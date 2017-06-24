package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3


fun BooleanArray.toFlowable(): Flowable<Boolean> = asIterable().toFlowable()
fun ByteArray.toFlowable(): Flowable<Byte> = asIterable().toFlowable()
fun ShortArray.toFlowable(): Flowable<Short> = asIterable().toFlowable()
fun IntArray.toFlowable(): Flowable<Int> = asIterable().toFlowable()
fun LongArray.toFlowable(): Flowable<Long> = asIterable().toFlowable()
fun FloatArray.toFlowable(): Flowable<Float> = asIterable().toFlowable()
fun DoubleArray.toFlowable(): Flowable<Double> = asIterable().toFlowable()
fun <T : Any> Array<T>.toFlowable(): Flowable<T> = Flowable.fromArray(*this)

fun IntProgression.toFlowable(): Flowable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Flowable.range(first, Math.max(0, last - first + 1))
        else Flowable.fromIterable(this)

fun <T : Any> Iterator<T>.toFlowable(): Flowable<T> = toIterable().toFlowable()
fun <T : Any> Iterable<T>.toFlowable(): Flowable<T> = Flowable.fromIterable(this)
fun <T : Any> Sequence<T>.toFlowable(): Flowable<T> = asIterable().toFlowable()

fun <T : Any> Iterable<Flowable<out T>>.merge(): Flowable<T> = Flowable.merge(this.toFlowable())
fun <T : Any> Iterable<Flowable<out T>>.mergeDelayError(): Flowable<T> = Flowable.mergeDelayError(this.toFlowable())

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


/**
 * Flowable.combineLatest(List<? extends Flowable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T : Any, R : Any> Iterable<Flowable<T>>.combineLatest(crossinline combineFunction: (args: List<T>) -> R): Flowable<R>
        = Flowable.combineLatest(this) { combineFunction(it.asList().map { it as T }) }

/**
 * Flowable.zip(List<? extends Flowable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T : Any, R : Any> Iterable<Flowable<T>>.zip(crossinline zipFunction: (args: List<T>) -> R): Flowable<R>
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

/**
 * Combine latest operator that produces [Pair]
 */
fun <T : Any, R : Any> Flowable<T>.combineLatest(flowable: Flowable<R>): Flowable<Pair<T, R>>
        = Flowable.combineLatest(this, flowable, BiFunction(::Pair))

/**
 * Combine latest operator that produces [Triple]
 */
fun <T : Any, R : Any, U : Any> Flowable<T>.combineLatest(flowable1: Flowable<R>, flowable2: Flowable<U>): Flowable<Triple<T, R, U>>
        = Flowable.combineLatest(this, flowable1, flowable2, Function3(::Triple))

//EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Flowable<Flowable<T>>. Same as calling `flatMap { it }`.
 */
fun <T : Any> Flowable<Flowable<T>>.mergeAll() = flatMap { it }


/**
 * Concatenates the emissions of an Flowable<Flowable<T>>. Same as calling `concatMap { it }`.
 */
fun <T : Any> Flowable<Flowable<T>>.concatAll() = concatMap { it }


/**
 * Emits the latest `Flowable<T>` emitted through an `Flowable<Flowable<T>>`. Same as calling `switchMap { it }`.
 */
fun <T : Any> Flowable<Flowable<T>>.switchLatest() = switchMap { it }


fun <T : Any> Flowable<Flowable<T>>.switchOnNext(): Flowable<T> = Flowable.switchOnNext(this)


/**
 * Collects `Pair` emission into a `Map`
 */
fun <A: Any, B: Any> Flowable<Pair<A, B>>.toMap() = toMap({it.first},{it.second})

/**
 * Collects `Pair` emission into a multimap
 */
fun <A: Any, B: Any> Flowable<Pair<A, B>>.toMultimap() = toMultimap({it.first},{it.second})