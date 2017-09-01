package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.Observable
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T : Any> T?.toMaybe(): Maybe<T> = Maybe.create { s -> if (this != null) s.onSuccess(this); s.onComplete() }
fun <T : Any> Future<T>.toMaybe(): Maybe<T> = Maybe.fromFuture(this)
fun <T : Any> Callable<T>.toMaybe(): Maybe<T> = Maybe.fromCallable(this)
fun <T : Any> (() -> T).toMaybe(): Maybe<T> = Maybe.fromCallable(this)

inline fun <reified R : Any> Maybe<Any>.cast(): Maybe<R> = cast(R::class.java)
inline fun <reified R : Any> Maybe<Any>.ofType(): Maybe<R> = ofType(R::class.java)



// EXTENSION FUNCTION OPERATORS

/**
 * Merges the emissions of a Observable<Maybe<T>>. Same as calling `flatMapMaybe { it }`.
 */
fun <T : Any> Observable<Maybe<T>>.mergeAllMaybes() = flatMapMaybe { it }

/**
 * Merges the emissions of a Flowable<Maybe<T>>. Same as calling `flatMap { it }`.
 */
fun <T : Any> Flowable<Maybe<T>>.mergeAllMaybes() = flatMapMaybe { it }

/**
 * Concats an Iterable of maybes into flowable. Same as calling `Maybe.concat(this)`
 */
fun <T : Any> Iterable<MaybeSource<T>>.concatMaybes() = Maybe.concat(this)
