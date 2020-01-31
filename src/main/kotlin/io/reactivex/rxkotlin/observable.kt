@file:Suppress("HasPlatformType", "unused")

package io.reactivex.rxkotlin

import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.Single


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun BooleanArray.toObservable(): Observable<Boolean> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun ByteArray.toObservable(): Observable<Byte> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun CharArray.toObservable(): Observable<Char> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun ShortArray.toObservable(): Observable<Short> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun IntArray.toObservable(): Observable<Int> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun LongArray.toObservable(): Observable<Long> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun FloatArray.toObservable(): Observable<Float> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun DoubleArray.toObservable(): Observable<Double> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Array<T>.toObservable(): Observable<T> = Observable.fromArray(*this)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun IntProgression.toObservable(): Observable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Observable.range(first, Math.max(0, last - first + 1))
        else Observable.fromIterable(this)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterator<T>.toObservable(): Observable<T> = toIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<T>.toObservable(): Observable<T> = Observable.fromIterable(this)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Sequence<T>.toObservable(): Observable<T> = asIterable().toObservable()

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<Observable<out T>>.merge(): Observable<T> = Observable.merge(this.toObservable())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<Observable<out T>>.mergeDelayError(): Observable<T> = Observable.mergeDelayError(this.toObservable())

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<out Iterable<T>>.flatMapIterable(): Observable<T> = flatMapIterable { it }

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<out Iterable<T>>.concatMapIterable(): Observable<T> = concatMapIterable { it }

/**
 * Returns Observable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one observable.
 * Works similar to [Observable.flatMap] and [Observable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source observable that returns [Sequence]
 * @returns Observable that merges all [Sequence]s produced by [body] functions
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, R : Any> Observable<T>.flatMapSequence(crossinline body: (T) -> Sequence<R>): Observable<R> =
        flatMap { body(it).toObservable() }


/**
 * Observable.combineLatest(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, R : Any> Iterable<Observable<T>>.combineLatest(crossinline combineFunction: (args: List<T>) -> R): Observable<R> =
        Observable.combineLatest(this) { combineFunction(it.asList().map { it as T }) }

/**
 * Observable.zip(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, R : Any> Iterable<Observable<T>>.zip(crossinline zipFunction: (args: List<T>) -> R): Observable<R> =
        Observable.zip(this) { zipFunction(it.asList().map { it as T }) }

/**
 * Returns an Observable that emits the items emitted by the source Observable, converted to the specified type.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified R : Any> Observable<*>.cast(): Observable<R> = cast(R::class.java)

/**
 * Filters the items emitted by an Observable, only emitting those of the specified type.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified R : Any> Observable<*>.ofType(): Observable<R> = ofType(R::class.java)

private fun <T : Any> Iterator<T>.toIterable(): Iterable<T> = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of an Observable<Observable<T>>. Same as calling `flatMap { it }`.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<Observable<T>>.mergeAll(): Observable<T> = flatMap { it }

/**
 * Concatenates the emissions of an Observable<Observable<T>>. Same as calling `concatMap { it }`.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<Observable<T>>.concatAll(): Observable<T> = concatMap { it }

/**
 * Emits the latest `Observable<T>` emitted through an `Observable<Observable<T>>`. Same as calling `switchMap { it }`.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<Observable<T>>.switchLatest(): Observable<T> = switchMap { it }

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<Observable<T>>.switchOnNext(): Observable<T> =
        Observable.switchOnNext(this)

/**
 * Collects `Pair` emission into a `Map`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <A : Any, B : Any> Observable<Pair<A, B>>.toMap(): Single<MutableMap<A, B>> =
        toMap({ it.first }, { it.second })

/**
 * Collects `Pair` emission into a multimap
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <A : Any, B : Any> Observable<Pair<A, B>>.toMultimap(): Single<MutableMap<A, MutableCollection<B>>> =
        toMultimap({ it.first }, { it.second })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<ObservableSource<T>>.concatAll(): Observable<T> =
        Observable.concat(this)
