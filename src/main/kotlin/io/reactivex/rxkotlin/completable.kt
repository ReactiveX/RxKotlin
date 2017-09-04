package io.reactivex.rxkotlin

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Action
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
fun  Observable<Completable>.mergeAllCompletables() = flatMapCompletable { it }

/**
 * Merges the emissions of a Flowable<Completable>. Same as calling `flatMap { it }`.
 */
fun  Flowable<Completable>.mergeAllCompletables() = flatMapCompletable { it }

/**
 * Concats an Iterable of completables into flowable. Same as calling `Completable.concat(this)`
 */
fun Iterable<CompletableSource>.concatAll() = Completable.concat(this)
