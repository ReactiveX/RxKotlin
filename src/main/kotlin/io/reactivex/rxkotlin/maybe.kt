package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.Observable
import java.util.concurrent.Callable
import java.util.concurrent.Future

@Deprecated(
        message = "Use Maybe.just and Maybe.empty respectively",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
                expression = "let { if(it != null) { Maybe.just(it)!! } else { Maybe.empty()!! } }",
                imports = "io.reactivex.Maybe"))
fun <T : Any> T?.toMaybe(): Maybe<T> = error("Deprecated")
@Deprecated(
        message = "Use Maybe.fromFuture instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
                expression = "let { Maybe.fromFuture(it) }",
                imports = "io.reactivex.Maybe"))
fun <T : Any> Future<T>.toMaybe(): Maybe<T> = error("Deprecated")
@Deprecated(
        message = "Use Maybe.fromCallable instead",
        level = DeprecationLevel.ERROR,
        replaceWith = ReplaceWith(
                expression = "let { Maybe.fromCallable(it) }",
                imports = "io.reactivex.Maybe"))
fun <T : Any> Callable<T>.toMaybe(): Maybe<T> = error("Deprecated")
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
fun <T : Any> Iterable<MaybeSource<T>>.concatAll() = Maybe.concat(this)
