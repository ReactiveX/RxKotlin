@file:Suppress("HasPlatformType", "unused")

package io.reactivex.rxkotlin

import io.reactivex.rxjava3.annotations.BackpressureKind
import io.reactivex.rxjava3.annotations.BackpressureSupport
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableSource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Action
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun Action.toCompletable(): Completable = Completable.fromAction(this)
fun Callable<out Any>.toCompletable(): Completable = Completable.fromCallable(this)
fun Future<out Any>.toCompletable(): Completable = Completable.fromFuture(this)
fun (() -> Any).toCompletable(): Completable = Completable.fromCallable(this)


// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Observable<Completable>. Same as calling `flatMapSingle { it }`.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun Observable<Completable>.mergeAllCompletables(): Completable = flatMapCompletable { it }

/**
 * Merges the emissions of a Flowable<Completable>. Same as calling `flatMap { it }`.
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun Flowable<Completable>.mergeAllCompletables(): Completable = flatMapCompletable { it }

/**
 * Concats an Iterable of completables into flowable. Same as calling `Completable.concat(this)`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun Iterable<CompletableSource>.concatAll(): Completable = Completable.concat(this)
