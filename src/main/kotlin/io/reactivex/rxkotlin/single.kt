@file:Suppress("HasPlatformType", "unused")

package io.reactivex.rxkotlin

import io.reactivex.rxjava3.annotations.BackpressureKind
import io.reactivex.rxjava3.annotations.BackpressureSupport
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource

inline fun <reified R : Any> Single<*>.cast(): Single<R> = cast(R::class.java)


// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Observable<Single<T>>. Same as calling `flatMapSingle { it }`.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<Single<T>>.mergeAllSingles(): Observable<T> = flatMapSingle { it }

/**
 * Merges the emissions of a Flowable<Single<T>>. Same as calling `flatMap { it }`.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<Single<T>>.mergeAllSingles(): Flowable<T> = flatMapSingle { it }

/**
 * Concats an Iterable of singles into flowable. Same as calling `Single.concat(this)`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
@BackpressureSupport(BackpressureKind.FULL)
fun <T : Any> Iterable<SingleSource<T>>.concatAll(): Flowable<T> = Single.concat(this)
