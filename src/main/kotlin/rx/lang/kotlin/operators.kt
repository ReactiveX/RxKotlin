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


fun <T> Observable<Observable<T>>.switchOnNext(): Observable<T> = Observable.switchOnNext(this)


/**
 * Joins the emissions of a finite `Observable` into a `String`.
 *
 * @param separator is the dividing character(s) between each element in the concatenated `String`
 *
 * @param prefix is the preceding `String` before the concatenated elements (optional)
 *
 * @param postfix is the succeeding `String` after the concatenated elements (optional)
 */
fun <T> Observable<T>.joinToString(separator: String? = null,
                                   prefix: String? = null,
                                   postfix: String? = null
) = withIndex()
        .collect( { StringBuilder(prefix ?: "") },
            { builder: StringBuilder, next: IndexedValue<T> -> builder.append(if (next.index == 0) "" else separator ?: "").append(next.value) }
        )
        .map {  it.append(postfix ?: "").toString() }
