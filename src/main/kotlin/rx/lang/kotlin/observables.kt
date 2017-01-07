package rx.lang.kotlin

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction

/**
 * [Observable.empty] alias
 */
fun <T : Any> emptyObservable(): Observable<T> = Observable.empty()

/**
 * [Observable.defer] alias
 */
fun <T : Any> Observable<T>.defer() = Observable.defer { this }

fun <T : Any> observable(body: (ObservableEmitter<in T>) -> Unit): Observable<T> = Observable.create(body)

private fun <T : Any> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

fun BooleanArray.toObservable(): Observable<Boolean> = Observable.fromArray(*this.toTypedArray())
fun ByteArray.toObservable(): Observable<Byte> = Observable.fromArray(*this.toTypedArray())
fun ShortArray.toObservable(): Observable<Short> = Observable.fromArray(*this.toTypedArray())
fun IntArray.toObservable(): Observable<Int> = Observable.fromArray(*this.toTypedArray())
fun LongArray.toObservable(): Observable<Long> = Observable.fromArray(*this.toTypedArray())
fun FloatArray.toObservable(): Observable<Float> = Observable.fromArray(*this.toTypedArray())
fun DoubleArray.toObservable(): Observable<Double> = Observable.fromArray(*this.toTypedArray())
fun <T : Any> Array<T>.toObservable(): Observable<T> = Observable.fromArray(*this)

fun IntProgression.toObservable(): Observable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Observable.range(first, Math.max(0, last - first + 1))
        else Observable.fromIterable(this)

fun <T : Any> Iterator<T>.toObservable(): Observable<T> = toIterable().toObservable()
fun <T : Any> Iterable<T>.toObservable(): Observable<T> = Observable.fromIterable(this)
fun <T : Any> Sequence<T>.toObservable(): Observable<T> = Observable.fromIterable(iterator().toIterable())

fun <T : Any> T.toSingletonObservable(): Observable<T> = Observable.just(this)
fun <T : Any> Throwable.toObservable(): Observable<T> = Observable.error(this)

fun <T : Any> Iterable<Observable<out T>>.merge(): Observable<T> = Observable.merge(this.toObservable())
fun <T : Any> Iterable<Observable<out T>>.mergeDelayError(): Observable<T> = Observable.mergeDelayError(this.toObservable())

inline fun <T : Any, R : Any> Observable<T>.fold(initial: R, crossinline body: (R, T) -> R): Single<R>
        = reduce(initial) { a, e -> body(a, e) }

fun <T : Any> Observable<T>.onError(block: (Throwable) -> Unit): Observable<T> = doOnError(block)

/**
 * Returns Observable that wrap all values into [IndexedValue] and populates corresponding index value.
 * Works similar to [kotlin.withIndex]
 */
fun <T : Any> Observable<T>.withIndex(): Observable<IndexedValue<T>>
        = zipWith(Observable.range(0, Int.MAX_VALUE), BiFunction { value, index -> IndexedValue(index, value) })

/**
 * Returns Observable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one observable.
 * Works similar to [Observable.flatMap] and [Observable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source observable that returns [Sequence]
 * @returns Observable that merges all [Sequence]s produced by [body] functions
 */
inline fun <T : Any, R : Any> Observable<T>.flatMapSequence(crossinline body: (T) -> Sequence<R>): Observable<R>
        = flatMap { body(it).toObservable() }

/**
 * Subscribe with a subscriber that is configured inside body
 */
inline fun <T : Any> Observable<T>.subscribeBy(body: FunctionSubscriberModifier<T>.() -> Unit): Disposable {
    val modifier = FunctionSubscriberModifier(subscriber<T>())
    modifier.body()
    subscribe(modifier.subscriber)
    return modifier.subscriber.origin!!
}

fun <T : Any> Observable<Observable<T>>.switchOnNext(): Observable<T> = Observable.switchOnNext(this)

/**
 * Observable.combineLatest(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, R> List<Observable<T>>.combineLatest(crossinline combineFunction: (args: List<T>) -> R): Observable<R>
        = Observable.combineLatest(this) { combineFunction(it.asList().map { it as T }) }

/**
 * Observable.zip(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, R> List<Observable<T>>.zip(crossinline zipFunction: (args: List<T>) -> R): Observable<R> =
        Observable.zip(this) { zipFunction(it.asList().map { it as T }) }

/**
 * Returns an Observable that emits the items emitted by the source Observable, converted to the specified type.
 */
inline fun <reified R : Any> Observable<*>.cast(): Observable<R> = cast(R::class.java)
