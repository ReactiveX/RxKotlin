package io.reactivex.rxkotlin

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.functions.*


object Singles {
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T : Any, U : Any, R : Any> zip(
            s1: SingleSource<T>,
            s2: SingleSource<U>,
            crossinline zipper: (T, U) -> R
    ): Single<R> = Single.zip(s1, s2, BiFunction { t, u -> zipper.invoke(t, u) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T : Any, U : Any> zip(
            s1: SingleSource<T>,
            s2: SingleSource<U>
    ): Single<Pair<T, U>> = Single.zip(s1, s2, BiFunction { t, u -> Pair(t, u) })


    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>, s3: SingleSource<T3>,
            crossinline zipper: (T1, T2, T3) -> R
    ): Single<R> = Single.zip(s1, s2, s3, Function3 { t1, t2, t3 -> zipper.invoke(t1, t2, t3) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any, T3 : Any>
            zip(
            s1: SingleSource<T1>,
            s2: SingleSource<T2>,
            s3: SingleSource<T3>
    ): Single<Triple<T1, T2, T3>> = Single.zip(s1, s2, s3, Function3 { t1, t2, t3 -> Triple(t1, t2, t3) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>,
            s3: SingleSource<T3>, s4: SingleSource<T4>,
            crossinline zipper: (T1, T2, T3, T4) -> R
    ): Single<R> = Single.zip(s1, s2, s3, s4,
            Function4 { t1, t2, t3, t4 -> zipper.invoke(t1, t2, t3, t4) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>,
            s3: SingleSource<T3>, s4: SingleSource<T4>,
            s5: SingleSource<T5>,
            crossinline zipper: (T1, T2, T3, T4, T5) -> R
    ): Single<R> = Single.zip(s1, s2, s3, s4, s5,
            Function5 { t1, t2, t3, t4, t5 -> zipper.invoke(t1, t2, t3, t4, t5) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>,
            s3: SingleSource<T3>, s4: SingleSource<T4>,
            s5: SingleSource<T5>, s6: SingleSource<T6>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6) -> R
    ): Single<R> = Single.zip(s1, s2, s3, s4, s5, s6,
            Function6 { t1, t2, t3, t4, t5, t6 -> zipper.invoke(t1, t2, t3, t4, t5, t6) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>,
            s3: SingleSource<T3>, s4: SingleSource<T4>,
            s5: SingleSource<T5>, s6: SingleSource<T6>,
            s7: SingleSource<T7>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Single<R> = Single.zip(s1, s2, s3, s4, s5, s6, s7,
            Function7 { t1, t2, t3, t4, t5, t6, t7 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>,
            s3: SingleSource<T3>, s4: SingleSource<T4>,
            s5: SingleSource<T5>, s6: SingleSource<T6>,
            s7: SingleSource<T7>, s8: SingleSource<T8>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Single<R> = Single.zip(s1, s2, s3, s4, s5, s6, s7, s8,
            Function8 { t1, t2, t3, t4, t5, t6, t7, t8 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8) })

    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, R : Any>
            zip(
            s1: SingleSource<T1>, s2: SingleSource<T2>,
            s3: SingleSource<T3>, s4: SingleSource<T4>,
            s5: SingleSource<T5>, s6: SingleSource<T6>,
            s7: SingleSource<T7>, s8: SingleSource<T8>,
            s9: SingleSource<T9>,
            crossinline zipper: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Single<R> = Single.zip(s1, s2, s3, s4, s5, s6, s7, s8, s9,
            Function9 { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8, t9) })
}

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, U : Any, R : Any> Single<T>.zipWith(
        other: SingleSource<U>,
        crossinline zipper: (T, U) -> R
): Single<R> = zipWith(other, BiFunction { t, u -> zipper.invoke(t, u) })


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Single<T>.zipWith(other: SingleSource<U>): Single<Pair<T, U>> =
        zipWith(other, BiFunction { t, u -> Pair(t, u) })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T: Any> List<Single<T>>.zipSingles(): Single<List<T>> {
    if (isEmpty()) return Single.just(emptyList())

    return Single.zip(this) {
        @Suppress("UNCHECKED_CAST")
        return@zip (it as Array<T>).toList()
    }
}
