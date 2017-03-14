package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Merges the emissions of an Observable<Observable<T>>. Same as calling `flatMap { it }`.
 */
fun <T : Any> Observable<Observable<T>>.mergeAll() = flatMap { it }

/**
 * Merges the emissions of a Flowable<Flowable<T>>. Same as calling `flatMap { it }`.
 */
fun <T : Any> Flowable<Flowable<T>>.mergeAll() = flatMap { it }


/**
 * Concatenates the emissions of an Observable<Observable<T>>. Same as calling `concatMap { it }`.
 */
fun <T : Any> Observable<Observable<T>>.concatAll() = concatMap { it }

/**
 * Concatenates the emissions of an Flowable<Flowable<T>>. Same as calling `concatMap { it }`.
 */
fun <T : Any> Flowable<Flowable<T>>.concatAll() = concatMap { it }


fun <T : Any> Observable<Observable<T>>.switchOnNext(): Observable<T> = Observable.switchOnNext(this)


fun <T : Any> Flowable<Flowable<T>>.switchOnNext(): Flowable<T> = Flowable.switchOnNext(this)


/**
 * Emits the latest `Observable<T>` emitted through an `Observable<Observable<T>>`. Same as calling `switchMap { it }`.
 */
fun <T : Any> Observable<Observable<T>>.switchLatest() = switchMap { it }


/**
 * Emits the latest `Flowable<T>` emitted through an `Flowable<Flowable<T>>`. Same as calling `switchMap { it }`.
 */
fun <T : Any> Flowable<Flowable<T>>.switchLatest() = switchMap { it }
