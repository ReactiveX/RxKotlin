package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import java.util.concurrent.Callable
import java.util.concurrent.Future

@Deprecated(
        message = "Use Single.just instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
                expression = "let { Single.just(it) }",
                imports = "io.reactivex.Single"))
fun <T : Any> T.toSingle(): Single<T> = error("Deprecated")
@Deprecated(
        message = "Use Single.fromFuture instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
                expression = "let { Single.fromFuture(it) }",
                imports = "io.reactivex.Single"))
fun <T : Any> Future<T>.toSingle(): Single<T> = error("Deprecated")
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
 * Concats an Iterable of singles into flowable. Same as calling `Single.concat(this)`
 */
fun <T : Any> Iterable<SingleSource<T>>.concatAll() = Single.concat(this)
