package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T : Any> T.toSingle(): Single<T> = Single.just(this)
fun <T : Any> Future<T>.toSingle(): Single<T> = Single.fromFuture(this)
fun <T : Any> Callable<T>.toSingle(): Single<T> = Single.fromCallable(this)
fun <T : Any> (() -> T).toSingle(): Single<T> = Single.fromCallable(this)

inline fun <reified R : Any> Single<Any>.cast(): Single<R> = cast(R::class.java)


// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Observable<Single<T>>. Same as calling `flatMapSingle { it }`.
 */
fun <T : Any> Observable<Single<T>>.mergeAllSingles() = flatMapSingle { it }

/**
 * Merges the emissions of a Flowable<Single<T>>. Same as calling `flatMap { it }`.
 */
fun <T : Any> Flowable<Single<T>>.mergeAllSingles() = flatMapSingle { it }

/**
 * Concatenates the emissions of an Flowable<Single<T>>.
 */
fun <T : Any> Observable<Single<T>>.concatAllSingles() = concatMap { it.toObservable() }

/**
 * Concatenates the emissions of an Flowable<Single<T>>.
 */
fun <T : Any> Flowable<Single<T>>.concatAllSingles() = concatMap { it.toFlowable() }

/**
 * Emits the latest `Single<T>` emitted through an `Flowable<Single<T>>`.
 */
fun <T : Any> Observable<Single<T>>.switchLatestSingle() = switchMap { it.toObservable() }

/**
 * Emits the latest `Single<T>` emitted through an `Flowable<Single<T>>`.
 */
fun <T : Any> Flowable<Single<T>>.switchLatestSingle() = switchMap { it.toFlowable() }

