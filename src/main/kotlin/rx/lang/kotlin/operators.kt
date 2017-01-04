package rx.lang.kotlin

import rx.Observable

/**
 * Merges the emissions of an Observable<Observable<T>>. Same as calling `flatMap { it }`.
 */
fun <T> Observable<Observable<T>>.mergeAll() = flatMap { it }

/**
 * Concatenates the emissions of an Observable<Observable<T>>. Same as calling `concatMap { it }`.
 */
fun <T> Observable<Observable<T>>.concatAll() = concatMap { it }

/**
 * Emits the latest `Observable<T>` emitted through an `Observable<Observable<T>>`. Same as calling `switchMap { it }`.
 */
fun <T> Observable<Observable<T>>.switchLatest() = switchMap { it }