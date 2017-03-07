package rx.lang.kotlin

import io.reactivex.Observable
import io.reactivex.Single

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


/**
 * Joins the emissions of a finite `Observable` into a `String`.
 *
 * @param separator is the dividing character(s) between each element in the concatenated `String`
 *
 * @param prefix is the preceding `String` before the concatenated elements (optional)
 *
 * @param postfix is the succeeding `String` after the concatenated elements (optional)
 */
fun <T : Any> Observable<T>.joinToString(separator: String? = null,
                                         prefix: String? = null,
                                         postfix: String? = null
): Single<String> = collect({ StringBuilder(prefix ?: "") }) { builder: StringBuilder, next: T ->
    builder.append(if (builder.length == prefix?.length ?: 0) "" else separator ?: "").append(next)
}.map { it.append(postfix ?: "").toString() }