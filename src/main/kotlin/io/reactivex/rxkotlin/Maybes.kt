@file:Suppress("unused")

package io.reactivex.rxkotlin

import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.functions.*

object Maybes {
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T : Any, U : Any, R : Any> zip(
            s1: MaybeSource<T>,
            s2: MaybeSource<U>,
            crossinline zipper: (T, U) -> R
    ): Maybe<R> = Maybe.zip(s1, s2,
            BiFunction { t, u -> zipper.invoke(t, u) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T : Any, U : Any> zip(
            s1: MaybeSource<T>,
            s2: MaybeSource<U>
    ): Maybe<Pair<T, U>> = Maybe.zip(s1, s2,
            BiFunction { t, u -> Pair(t, u) })


    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>, s3: MaybeSource<T3>,
            crossinline zipper: (T1, T2, T3) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3,
            Function3 { t1, t2, t3 -> zipper.invoke(t1, t2, t3) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any, T3 : Any>
            zip(
            s1: MaybeSource<T1>,
            s2: MaybeSource<T2>,
            s3: MaybeSource<T3>
    ): Maybe<Triple<T1, T2, T3>> = Maybe.zip(s1, s2, s3,
            Function3 { t1, t2, t3 -> Triple(t1, t2, t3) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>,
            s3: MaybeSource<T3>, s4: MaybeSource<T4>,
            crossinline zipper: (T1, T2, T3, T4) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3, s4,
            Function4 { t1, t2, t3, t4 -> zipper.invoke(t1, t2, t3, t4) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>,
            s3: MaybeSource<T3>, s4: MaybeSource<T4>,
            s5: MaybeSource<T5>,
            crossinline zipper: (T1, T2, T3, T4, T5) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3, s4, s5,
            Function5 { t1, t2, t3, t4, t5 -> zipper.invoke(t1, t2, t3, t4, t5) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>,
            s3: MaybeSource<T3>, s4: MaybeSource<T4>,
            s5: MaybeSource<T5>, s6: MaybeSource<T6>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3, s4, s5, s6,
            Function6 { t1, t2, t3, t4, t5, t6 -> zipper.invoke(t1, t2, t3, t4, t5, t6) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>,
            s3: MaybeSource<T3>, s4: MaybeSource<T4>,
            s5: MaybeSource<T5>, s6: MaybeSource<T6>,
            s7: MaybeSource<T7>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3, s4, s5, s6, s7,
            Function7 { t1, t2, t3, t4, t5, t6, t7 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>,
            s3: MaybeSource<T3>, s4: MaybeSource<T4>,
            s5: MaybeSource<T5>, s6: MaybeSource<T6>,
            s7: MaybeSource<T7>, s8: MaybeSource<T8>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3, s4, s5, s6, s7, s8,
            Function8 { t1, t2, t3, t4, t5, t6, t7, t8 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, R : Any>
            zip(
            s1: MaybeSource<T1>, s2: MaybeSource<T2>,
            s3: MaybeSource<T3>, s4: MaybeSource<T4>,
            s5: MaybeSource<T5>, s6: MaybeSource<T6>,
            s7: MaybeSource<T7>, s8: MaybeSource<T8>,
            s9: MaybeSource<T9>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Maybe<R> = Maybe.zip(s1, s2, s3, s4, s5, s6, s7, s8, s9,
            Function9 { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8, t9) })
}

/**
 * An alias to [Maybe.zipWith], but allowing for cleaner lambda syntax.
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, U : Any, R : Any> Maybe<T>.zipWith(
        other: MaybeSource<U>,
        crossinline zipper: (T, U) -> R
): Maybe<R> = zipWith(other, BiFunction { t, u -> zipper.invoke(t, u) })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Maybe<T>.zipWith(other: MaybeSource<U>): Maybe<Pair<T, U>> =
        zipWith(other, BiFunction { t, u -> Pair(t, u) })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T: Any> List<Maybe<T>>.zipMaybes(): Maybe<List<T>> {
    if (isEmpty()) return Maybe.just(emptyList())

    return Maybe.zip(this) {
        @Suppress("UNCHECKED_CAST")
        return@zip (it as Array<T>).toList()
    }
}
