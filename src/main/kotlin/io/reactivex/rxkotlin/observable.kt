package io.reactivex.rxkotlin

import io.reactivex.Observable
import io.reactivex.ObservableSource


fun BooleanArray.toObservable(): Observable<Boolean> = asIterable().toObservable()
fun ByteArray.toObservable(): Observable<Byte> = asIterable().toObservable()
fun CharArray.toObservable(): Observable<Char> = asIterable().toObservable()
fun ShortArray.toObservable(): Observable<Short> = asIterable().toObservable()
fun IntArray.toObservable(): Observable<Int> = asIterable().toObservable()
fun LongArray.toObservable(): Observable<Long> = asIterable().toObservable()
fun FloatArray.toObservable(): Observable<Float> = asIterable().toObservable()
fun DoubleArray.toObservable(): Observable<Double> = asIterable().toObservable()
fun <T : Any> Array<T>.toObservable(): Observable<T> = Observable.fromArray(*this)

fun IntProgression.toObservable(): Observable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Observable.range(first, Math.max(0, last - first + 1))
        else Observable.fromIterable(this)

fun <T : Any> Iterator<T>.toObservable(): Observable<T> = toIterable().toObservable()
fun <T : Any> Iterable<T>.toObservable(): Observable<T> = Observable.fromIterable(this)
fun <T : Any> Sequence<T>.toObservable(): Observable<T> = asIterable().toObservable()

fun <T : Any> Iterable<Observable<out T>>.merge(): Observable<T> = Observable.merge(this.toObservable())
fun <T : Any> Iterable<Observable<out T>>.mergeDelayError(): Observable<T> = Observable.mergeDelayError(this.toObservable())

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
 * Observable.combineLatest(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T : Any, R : Any> Iterable<Observable<T>>.combineLatest(crossinline combineFunction: (args: List<T>) -> R): Observable<R>
        = Observable.combineLatest(this) { combineFunction(it.asList().map { it as T }) }

/**
 * Observable.zip(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
inline fun <T : Any, R : Any> Iterable<Observable<T>>.zip(crossinline zipFunction: (args: List<T>) -> R): Observable<R>
        = Observable.zip(this) { zipFunction(it.asList().map { it as T }) }

/**
 * Returns an Observable that emits the items emitted by the source Observable, converted to the specified type.
 */
inline fun <reified R : Any> Observable<*>.cast(): Observable<R> = cast(R::class.java)

/**
 * Filters the items emitted by an Observable, only emitting those of the specified type.
 */
inline fun <reified R : Any> Observable<*>.ofType(): Observable<R> = ofType(R::class.java)

private fun <T : Any> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of an Observable<Observable<T>>. Same as calling `flatMap { it }`.
 */
fun <T : Any> Observable<Observable<T>>.mergeAll() = flatMap { it }

/**
 * Concatenates the emissions of an Observable<Observable<T>>. Same as calling `concatMap { it }`.
 */
fun <T : Any> Observable<Observable<T>>.concatAll() = concatMap { it }

/**
 * Emits the latest `Observable<T>` emitted through an `Observable<Observable<T>>`. Same as calling `switchMap { it }`.
 */
fun <T : Any> Observable<Observable<T>>.switchLatest() = switchMap { it }

fun <T : Any> Observable<Observable<T>>.switchOnNext(): Observable<T> = Observable.switchOnNext(this)

/**
 * Collects `Pair` emission into a `Map`
 */
fun <A: Any, B: Any> Observable<Pair<A,B>>.toMap() = toMap({it.first},{it.second})

/**
 * Collects `Pair` emission into a multimap
 */
fun <A: Any, B: Any> Observable<Pair<A,B>>.toMultimap() = toMultimap({it.first},{it.second})

fun  <T : Any> Iterable<ObservableSource<T>>.concatAll() = Observable.concat(this)
