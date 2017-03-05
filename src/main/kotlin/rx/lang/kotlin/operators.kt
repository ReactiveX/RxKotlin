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
 * Joins the emissions of a finite `Observable` into a `String`.
 *
 * The `prefix` is appended to the front of the joined `String`.
 *
 * The `postfix` is appended to the end of the joined `String`.
 *
*/
fun <T> Observable<T>.joinToString(separator: String? = null,
                                   prefix: String? = null,
                                   postfix: String? = null
) = Observable.fromCallable { StringBuilder(prefix?:"") }.flatMap { sb ->
    this.map { it.toString() }
            .let {
                if (separator == null)
                    it.doOnNext { sb.append(it) }
                else
                    it.withIndex().doOnNext {
                        if (it.index > 0) {
                            sb.append(separator)
                        }
                        sb.append(it.value)
                    }
            }.count()
            .doOnNext { sb.append(postfix?:"") }
            .map { sb.toString() }
}
