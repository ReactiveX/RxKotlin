package io.reactivex.rxkotlin

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.*


object Singles {
    inline fun <T, U, R> zip(s1: SingleSource<T>, s2: SingleSource<U>, crossinline zipper: (T, U) -> R): Single<R>
            = Single.zip(s1, s2, BiFunction { t, u -> zipper.invoke(t, u) })

    fun <T, U> zip(s1: SingleSource<T>, s2: SingleSource<U>): Single<Pair<T,U>>
            = Single.zip(s1, s2, BiFunction { t, u -> Pair(t,u) })


    inline fun <T1, T2, T3, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>, s3: SingleSource<T3>,
                crossinline zipper: (T1, T2, T3) -> R): Single<R>
            = Single.zip(s1, s2, s3, Function3 { t1, t2, t3 -> zipper.invoke(t1, t2, t3) })

    fun <T1, T2, T3>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>, s3: SingleSource<T3>): Single<Triple<T1,T2,T3>>
            = Single.zip(s1, s2, s3, Function3 { t1, t2, t3 -> Triple(t1,t2,t3) })

    inline fun <T1, T2, T3, T4, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>,
                s3: SingleSource<T3>, s4: SingleSource<T4>,
                crossinline zipper: (T1, T2, T3, T4) -> R): Single<R>
            = Single.zip(s1, s2, s3, s4, Function4 { t1, t2, t3, t4 -> zipper.invoke(t1, t2, t3, t4) })

    inline fun <T1, T2, T3, T4, T5, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>,
                s3: SingleSource<T3>, s4: SingleSource<T4>,
                s5: SingleSource<T5>,
                crossinline zipper: (T1, T2, T3, T4, T5) -> R): Single<R>
            = Single.zip(s1, s2, s3, s4, s5, Function5 { t1, t2, t3, t4, t5 -> zipper.invoke(t1, t2, t3, t4, t5) })

    inline fun <T1, T2, T3, T4, T5, T6, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>,
                s3: SingleSource<T3>, s4: SingleSource<T4>,
                s5: SingleSource<T5>, s6: SingleSource<T6>,
                crossinline zipper: (T1, T2, T3, T4, T5, T6) -> R): Single<R>
            = Single.zip(s1, s2, s3, s4, s5, s6, Function6 { t1, t2, t3, t4, t5, t6 -> zipper.invoke(t1, t2, t3, t4, t5, t6) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>,
                s3: SingleSource<T3>, s4: SingleSource<T4>,
                s5: SingleSource<T5>, s6: SingleSource<T6>,
                s7: SingleSource<T7>,
                crossinline zipper: (T1, T2, T3, T4, T5, T6, T7) -> R): Single<R>
            = Single.zip(s1, s2, s3, s4, s5, s6, s7, Function7 { t1, t2, t3, t4, t5, t6, t7 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, T8, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>,
                s3: SingleSource<T3>, s4: SingleSource<T4>,
                s5: SingleSource<T5>, s6: SingleSource<T6>,
                s7: SingleSource<T7>, s8: SingleSource<T8>,
                crossinline zipper: (T1, T2, T3, T4, T5, T6, T7, T8) -> R): Single<R>
            = Single.zip(s1, s2, s3, s4, s5, s6, s7, s8, Function8 { t1, t2, t3, t4, t5, t6, t7, t8 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R>
            zip(s1: SingleSource<T1>, s2: SingleSource<T2>,
                s3: SingleSource<T3>, s4: SingleSource<T4>,
                s5: SingleSource<T5>, s6: SingleSource<T6>,
                s7: SingleSource<T7>, s8: SingleSource<T8>,
                s9: SingleSource<T9>,
                crossinline zipper: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R): Single<R>
            = Single.zip(s1, s2, s3, s4, s5, s6, s7, s8, s9, Function9 { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> zipper.invoke(t1, t2, t3, t4, t5, t6, t7, t8, t9) })
}

inline fun <T, U, R> Single<T>.zipWith(other: SingleSource<U>, crossinline zipper: (T, U) -> R): Single<R>
        = zipWith(other, BiFunction { t, u -> zipper.invoke(t, u) })


fun <T, U> Single<T>.zipWith(other: SingleSource<U>): Single<Pair<T,U>>
        = zipWith(other, BiFunction { t, u -> Pair(t,u) })