package rx.lang.kotlin

import rx.Observable
import rx.functions.Func1
import rx.observables.BlockingObservable

fun BooleanArray.toObservable(): Observable<Boolean> = this.asIterable().toObservable()
fun ByteArray.toObservable(): Observable<Byte> = this.asIterable().toObservable()
fun ShortArray.toObservable(): Observable<Short> = this.asIterable().toObservable()
fun IntArray.toObservable(): Observable<Int> = this.asIterable().toObservable()
fun LongArray.toObservable(): Observable<Long> = this.asIterable().toObservable()
fun FloatArray.toObservable(): Observable<Float> = this.asIterable().toObservable()
fun DoubleArray.toObservable(): Observable<Double> = this.asIterable().toObservable()
fun <T> Array<out T>.toObservable(): Observable<T> = Observable.from(this)

fun IntProgression.toObservable(): Observable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Observable.range(first, Math.max(0, last - first + 1))
        else Observable.from(this)

fun <T> Iterator<T>.toObservable(): Observable<T> = toIterable().toObservable()
fun <T> Iterable<T>.toObservable(): Observable<T> = Observable.from(this)
fun <T> Sequence<T>.toObservable(): Observable<T> = asIterable().toObservable()

fun <T> Throwable.toObservable(): Observable<T> = Observable.error(this)

fun <T> Iterable<Observable<out T>>.merge(): Observable<T> = Observable.merge(this.toObservable())
fun <T> Iterable<Observable<out T>>.mergeDelayError(): Observable<T> = Observable.mergeDelayError(this.toObservable())

@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
fun <T> Observable<T>.firstOrNull(): Observable<T?> = firstOrDefault(null)

fun <T> BlockingObservable<T>.firstOrNull(): T = firstOrDefault(null)

@Suppress("RedundantSamConstructor")
fun <T> Observable<T>.onErrorReturnNull(): Observable<T?> = onErrorReturn(Func1<Throwable, T?> { t -> null })

/**
 * Returns [Observable] that requires all objects to be non null. Raising [NullPointerException] in case of null object
 */
fun <T : Any> Observable<T?>.requireNoNulls(): Observable<T> = map { it ?: throw NullPointerException("null element found in rx observable") }

/**
 * Returns [Observable] with non-null generic type T. Returned observable filter out null values
 */
@Suppress("UNCHECKED_CAST") fun <T : Any> Observable<T?>.filterNotNull(): Observable<T> = filter { it != null } as Observable<T>

/**
 * Returns Observable that wrap all values into [IndexedValue] and populates corresponding index value.
 */
fun <T> Observable<T>.withIndex(): Observable<IndexedValue<T>> =
        zipWith(Observable.range(0, Int.MAX_VALUE)) { value, index -> IndexedValue(index, value) }

/**
 * Returns Observable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one observable.
 * Works similar to [Observable.flatMap] and [Observable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source observable that returns [Sequence]
 *  @returns Observable that merges all [Sequence]s produced by [body] functions
 */
fun <T, R> Observable<T>.flatMapSequence(body: (T) -> Sequence<R>): Observable<R> = flatMap { body(it).toObservable() }

fun <T> Observable<Observable<T>>.switchOnNext(): Observable<T> = Observable.switchOnNext(this)

/**
 * Observable.combineLatest(Iterable<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
fun <T, R> Iterable<Observable<T>>.combineLatest(combineFunction: (args: List<T>) -> R): Observable<R> =
        Observable.combineLatest(this, { combineFunction(it.asList() as List<T>) })

/**
 * Observable.zip(Iterable<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
fun <T, R> Iterable<Observable<T>>.zip(zipFunction: (args: List<T>) -> R): Observable<R> =
        Observable.zip(this, { zipFunction(it.asList() as List<T>) })

/**
 * Returns an Observable that emits the items emitted by the source Observable, converted to the specified type.
 */
inline fun <reified R : Any> Observable<*>.cast(): Observable<R> = cast(R::class.java)

/**
 * Filters the items emitted by an Observable, only emitting those of the specified type.
 */
inline fun <reified R : Any> Observable<*>.ofType(): Observable<R> = ofType(R::class.java)

private fun <T> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}