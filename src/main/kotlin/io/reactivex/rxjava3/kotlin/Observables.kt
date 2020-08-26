@file:Suppress("unused", "HasPlatformType")

package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.*

/**
 * SAM adapters to aid Kotlin lambda support
 */
object Observables {

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, R : Any> combineLatest(
            source1: Observable<T1>,
            source2: Observable<T2>,
            crossinline combineFunction: (T1, T2) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2,
            { t1, t2 -> combineFunction(t1, t2) })

    /**
     * Emits `Pair<T1,T2>`
     */
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any> combineLatest(source1: Observable<T1>, source2: Observable<T2>): Observable<Pair<T1, T2>> =
            Observable.combineLatest(source1, source2, { t1, t2 -> t1 to t2 })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> combineLatest(
            source1: Observable<T1>,
            source2: Observable<T2>,
            source3: Observable<T3>,
            crossinline combineFunction: (T1, T2, T3) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3,
            { t1, t2, t3 -> combineFunction(t1, t2, t3) })

    /**
     * Emits `Triple<T1,T2,T3>`
     */
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any, T3 : Any> combineLatest(
            source1: Observable<T1>,
            source2: Observable<T2>,
            source3: Observable<T3>
    ): Observable<Triple<T1, T2, T3>> = Observable.combineLatest(source1, source2, source3,
            { t1, t2, t3 -> Triple(t1, t2, t3) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>,
            source4: Observable<T4>, crossinline combineFunction: (T1, T2, T3, T4) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4,
            { t1, t2, t3, t4 -> combineFunction(t1, t2, t3, t4) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, source5, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, crossinline combineFunction: (T1, T2, T3, T4, T5) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5,
            { t1, t2, t3, t4, t5 -> combineFunction(t1, t2, t3, t4, t5) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, source5, source6, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6,
            { t1, t2, t3, t4, t5, t6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7,
            { t1, t2, t3, t4, t5, t6, t7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8,
            { t1, t2, t3, t4, t5, t6, t7, t8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, source9, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            source9: Observable<T9>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, source9,
            { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, R : Any> zip(
            source1: Observable<T1>,
            source2: Observable<T2>,
            crossinline combineFunction: (T1, T2) -> R
    ): Observable<R> = Observable.zip(source1, source2,
            { t1, t2 -> combineFunction(t1, t2) })


    /**
     * Emits `Pair<T1,T2>`
     */
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any> zip(source1: Observable<T1>, source2: Observable<T2>): Observable<Pair<T1, T2>> =
            Observable.zip(source1, source2, { t1, t2 -> t1 to t2 })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> zip(
            source1: Observable<T1>,
            source2: Observable<T2>,
            source3: Observable<T3>,
            crossinline combineFunction: (T1, T2, T3) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3,
            { t1, t2, t3 -> combineFunction(t1, t2, t3) })

    /**
     * Emits `Triple<T1,T2,T3>`
     */
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any, T3 : Any> zip(
            source1: Observable<T1>,
            source2: Observable<T2>,
            source3: Observable<T3>
    ): Observable<Triple<T1, T2, T3>> = Observable.zip(source1, source2, source3,
            { t1, t2, t3 -> Triple(t1, t2, t3) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, source4, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> zip(
            source1: Observable<T1>,
            source2: Observable<T2>,
            source3: Observable<T3>,
            source4: Observable<T4>,
            crossinline combineFunction: (T1, T2, T3, T4) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4,
            { t1, t2, t3, t4 -> combineFunction(t1, t2, t3, t4) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, source4, source5, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, crossinline combineFunction: (T1, T2, T3, T4, T5) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5,
            { t1, t2, t3, t4, t5 -> combineFunction(t1, t2, t3, t4, t5) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, source4, source5, source6, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6,
            { t1, t2, t3, t4, t5, t6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, source4, source5, source6, source7, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, R : Any> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6, source7,
            { t1, t2, t3, t4, t5, t6, t7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, R : Any> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8,
            { t1, t2, t3, t4, t5, t6, t7, t8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8, source9, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, R : Any> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            source9: Observable<T9>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8, source9,
            { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })

}


/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(other, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, U : Any, R : Any> Observable<T>.withLatestFrom(
        other: ObservableSource<U>,
        crossinline combiner: (T, U) -> R
): Observable<R> = withLatestFrom(other, { t, u -> combiner(t, u) })

/**
 * Emits a `Pair`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Observable<T>.withLatestFrom(other: ObservableSource<U>): Observable<Pair<T, U>> =
        withLatestFrom(other, { t, u -> Pair(t, u) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, R : Any> Observable<T>.withLatestFromm(
        o1: ObservableSource<T1>,
        o2: ObservableSource<T2>,
        crossinline combiner: (T, T1, T2) -> R
): Observable<R> = withLatestFrom(o1, o2, { t, t1, t2 -> combiner(t, t1, t2) })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, T1 : Any, T2 : Any> Observable<T>.withLatestFrom(
        o1: ObservableSource<T1>,
        o2: ObservableSource<T2>
): Observable<Triple<T, T1, T2>> = withLatestFrom(o1, o2, { t, t1, t2 -> Triple(t, t1, t2) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, o3, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, T3 : Any, R : Any> Observable<T>.withLatestFrom(
        o1: ObservableSource<T1>,
        o2: ObservableSource<T2>,
        o3: ObservableSource<T3>,
        crossinline combiner: (T, T1, T2, T3) -> R
): Observable<R> = withLatestFrom(o1, o2, o3, { t, t1, t2, t3 -> combiner(t, t1, t2, t3) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, o3, o4, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> Observable<T>.withLatestFrom(
        o1: ObservableSource<T1>,
        o2: ObservableSource<T2>,
        o3: ObservableSource<T3>,
        o4: ObservableSource<T4>,
        crossinline combiner: (T, T1, T2, T3, T4) -> R
): Observable<R> = withLatestFrom(o1, o2, o3, o4,
        { t, t1, t2, t3, t4 -> combiner(t, t1, t2, t3, t4) })

/**
 * An alias to [Observable.zipWith], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("zipWith(other, zipper)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, U : Any, R : Any> Observable<T>.zipWith(
        other: ObservableSource<U>,
        crossinline zipper: (T, U) -> R
): Observable<R> = zipWith(other, { t, u -> zipper(t, u) })

/**
 * Emits a zipped `Pair`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Observable<T>.zipWith(other: ObservableSource<U>): Observable<Pair<T, U>> =
        zipWith(other, { t, u -> Pair(t, u) })
