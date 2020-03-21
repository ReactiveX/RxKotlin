@file:Suppress("HasPlatformType", "unused")

package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.annotations.BackpressureKind
import io.reactivex.rxjava3.annotations.BackpressureSupport
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.*
import org.reactivestreams.Publisher


@CheckReturnValue
fun BooleanArray.toFlowable(): Flowable<Boolean> = asIterable().toFlowable()

@CheckReturnValue
fun ByteArray.toFlowable(): Flowable<Byte> = asIterable().toFlowable()

@CheckReturnValue
fun CharArray.toFlowable(): Flowable<Char> = asIterable().toFlowable()

@CheckReturnValue
fun ShortArray.toFlowable(): Flowable<Short> = asIterable().toFlowable()

@CheckReturnValue
fun IntArray.toFlowable(): Flowable<Int> = asIterable().toFlowable()

@CheckReturnValue
fun LongArray.toFlowable(): Flowable<Long> = asIterable().toFlowable()

@CheckReturnValue
fun FloatArray.toFlowable(): Flowable<Float> = asIterable().toFlowable()

@CheckReturnValue
fun DoubleArray.toFlowable(): Flowable<Double> = asIterable().toFlowable()

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Array<T>.toFlowable(): Flowable<T> = Flowable.fromArray(*this)

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun IntProgression.toFlowable(): Flowable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Flowable.range(first, Math.max(0, last - first + 1))
        else Flowable.fromIterable(this)

fun <T : Any> Iterator<T>.toFlowable(): Flowable<T> = toIterable().toFlowable()
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<T>.toFlowable(): Flowable<T> = Flowable.fromIterable(this)

fun <T : Any> Sequence<T>.toFlowable(): Flowable<T> = asIterable().toFlowable()

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<Flowable<out T>>.merge(): Flowable<T> = Flowable.merge(this.toFlowable())

@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<Flowable<out T>>.mergeDelayError(): Flowable<T> = Flowable.mergeDelayError(this.toFlowable())

/**
 * Returns Flowable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one flowable.
 * Works similar to [Flowable.flatMap] and [Flowable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source flowable that returns [Sequence]
 * @returns Flowable that merges all [Sequence]s produced by [body] functions
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, R : Any> Flowable<T>.flatMapSequence(crossinline body: (T) -> Sequence<R>): Flowable<R> =
        flatMap { body(it).toFlowable() }


/**
 * Flowable.combineLatest(List<? extends Flowable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
@SchedulerSupport(SchedulerSupport.NONE)
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
inline fun <T : Any, R : Any> Iterable<Flowable<T>>.combineLatest(crossinline combineFunction: (args: List<T>) -> R): Flowable<R> =
        Flowable.combineLatest(this) { combineFunction(it.asList().map { it as T }) }

/**
 * Flowable.zip(List<? extends Flowable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, R : Any> Iterable<Flowable<T>>.zip(crossinline zipFunction: (args: List<T>) -> R): Flowable<R> =
        Flowable.zip(this) { zipFunction(it.asList().map { it as T }) }

/**
 * Returns an Flowable that emits the items emitted by the source Flowable, converted to the specified type.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified R : Any> Flowable<*>.cast(): Flowable<R> = cast(R::class.java)

/**
 * Filters the items emitted by an Flowable, only emitting those of the specified type.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified R : Any> Flowable<*>.ofType(): Flowable<R> = ofType(R::class.java)

private fun <T : Any> Iterator<T>.toIterable(): Iterable<T> = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

/**
 * Combine latest operator that produces [Pair]
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, R : Any> Flowable<T>.combineLatest(flowable: Flowable<R>): Flowable<Pair<T, R>> =
        Flowable.combineLatest(this, flowable, BiFunction(::Pair))

/**
 * Combine latest operator that produces [Triple]
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, R : Any, U : Any> Flowable<T>.combineLatest(
        flowable1: Flowable<R>,
        flowable2: Flowable<U>
): Flowable<Triple<T, R, U>> = Flowable.combineLatest(this, flowable1, flowable2, Function3(::Triple))

//EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Flowable<Flowable<T>>. Same as calling `flatMap { it }`.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Flowable<T>>.mergeAll(): Flowable<T> = flatMap { it }


/**
 * Concatenates the emissions of an Flowable<Flowable<T>>. Same as calling `concatMap { it }`.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Flowable<T>>.concatAll(): Flowable<T> = concatMap { it }


/**
 * Emits the latest `Flowable<T>` emitted through an `Flowable<Flowable<T>>`. Same as calling `switchMap { it }`.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Flowable<T>>.switchLatest(): Flowable<T> = switchMap { it }


@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Flowable<T>>.switchOnNext(): Flowable<T> = Flowable.switchOnNext(this)


/**
 * Collects `Pair` emission into a `Map`
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <A : Any, B : Any> Flowable<Pair<A, B>>.toMap(): Single<MutableMap<A, B>> =
        toMap({ it.first }, { it.second })

/**
 * Collects `Pair` emission into a multimap
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <A : Any, B : Any> Flowable<Pair<A, B>>.toMultimap(): Single<MutableMap<A, MutableCollection<B>>> =
        toMultimap({ it.first }, { it.second })

/**
 * Concats an Iterable of flowables into flowable. Same as calling `Flowable.concat(this)`
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<Publisher<T>>.concatAll(): Flowable<T> = Flowable.concat(this)
