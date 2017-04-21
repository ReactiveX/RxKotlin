package io.reactivex.rxkotlin

import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.functions.BiFunction

object Maybes {
    inline fun <T, U, R> zip(s1: MaybeSource<T>, s2: MaybeSource<U>, crossinline zipper: (T, U) -> R): MaybeSource<R>
            = Maybe.zip(s1,s2, BiFunction { t, u -> zipper.invoke(t, u) })
}

/**
 * An alias to [Maybe.zipWith], but allowing for cleaner lambda syntax.
 */
inline fun <T, U, R> Maybe<T>.zipWith(other: MaybeSource<U>, crossinline zipper: (T, U) -> R): Maybe<R>
        = zipWith(other, BiFunction { t, u -> zipper.invoke(t, u) })
