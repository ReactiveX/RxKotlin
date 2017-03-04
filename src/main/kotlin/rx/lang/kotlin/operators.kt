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


/**
 * Joins the emissions of a finite `Observable` into a `String`
*/
fun <T> Observable<T>.joinToString(separator: String? = null,
                                   prefix: String? = null,
                                   postfix: String? = null,
                                   limit: Int = -1,
                                   truncated: String = "..."
) = map { it.toString() }
        .let { if (limit <= 0) it else Observable.concat(it.take(limit), Observable.just(truncated)) }
        .let {
            if (separator == null)
                it.reduce("") { rolling, next -> rolling + next }
            else
                it.withIndex().reduce("") { rolling, next -> rolling + (if (next.index == 0) "" else separator) + next.value }
        }.let { if (prefix == null) it else it.map { str -> prefix + str } }
        .let { if (postfix == null) it else it.map { str -> str + postfix } }
