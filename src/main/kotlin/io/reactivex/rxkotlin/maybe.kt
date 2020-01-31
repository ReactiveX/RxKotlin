@file:Suppress("HasPlatformType", "unused")

package io.reactivex.rxkotlin

import io.reactivex.rxjava3.annotations.BackpressureKind
import io.reactivex.rxjava3.annotations.BackpressureSupport
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeSource
import io.reactivex.rxjava3.core.Observable


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified R : Any> Maybe<*>.cast(): Maybe<R> = cast(R::class.java)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified R : Any> Maybe<*>.ofType(): Maybe<R> = ofType(R::class.java)


// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Observable<Maybe<T>>. Same as calling `flatMapMaybe { it }`.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<Maybe<T>>.mergeAllMaybes(): Observable<T> = flatMapMaybe { it }

/**
 * Merges the emissions of a Flowable<Maybe<T>>. Same as calling `flatMap { it }`.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Maybe<T>>.mergeAllMaybes(): Flowable<T> = flatMapMaybe { it }

/**
 * Concats an Iterable of maybes into flowable. Same as calling `Maybe.concat(this)`
 */
@BackpressureSupport(BackpressureKind.FULL)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Iterable<MaybeSource<T>>.concatAll(): Flowable<T> = Maybe.concat(this)
