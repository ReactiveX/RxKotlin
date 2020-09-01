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
            BiFunction<T1, T2, R> { t1, t2 -> combineFunction(t1, t2) })

    /**
     * Emits `Pair<T1,T2>`
     */
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any> combineLatest(source1: Observable<T1>, source2: Observable<T2>): Observable<Pair<T1, T2>> =
            Observable.combineLatest(source1, source2,
                    BiFunction<T1, T2, Pair<T1, T2>> { t1, t2 -> t1 to t2 })

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
            Function3 { t1: T1, t2: T2, t3: T3 -> combineFunction(t1, t2, t3) })

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
            Function3<T1, T2, T3, Triple<T1, T2, T3>> { t1, t2, t3 -> Triple(t1, t2, t3) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Observable.combineLatest(source1, source2, source3, source4, combineFunction)", "io.reactivex.Observable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>,
            source4: Observable<T4>, crossinline combineFunction: (T1, T2, T3, T4) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4,
            Function4 { t1: T1, t2: T2, t3: T3, t4: T4 -> combineFunction(t1, t2, t3, t4) })


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
            Function5 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> combineFunction(t1, t2, t3, t4, t5) })


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
            Function6 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

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
            Function7 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })


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
            Function8 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

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
            Function9 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })


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
            BiFunction<T1, T2, R> { t1, t2 -> combineFunction(t1, t2) })


    /**
     * Emits `Pair<T1,T2>`
     */
    @CheckReturnValue
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any> zip(source1: Observable<T1>, source2: Observable<T2>): Observable<Pair<T1, T2>> =
            Observable.zip(source1, source2,
                    BiFunction<T1, T2, Pair<T1, T2>> { t1, t2 -> t1 to t2 })

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
            Function3 { t1: T1, t2: T2, t3: T3 -> combineFunction(t1, t2, t3) })

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
            Function3<T1, T2, T3, Triple<T1, T2, T3>> { t1, t2, t3 -> Triple(t1, t2, t3) })

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
            Function4 { t1: T1, t2: T2, t3: T3, t4: T4 -> combineFunction(t1, t2, t3, t4) })

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
            Function5 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> combineFunction(t1, t2, t3, t4, t5) })


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
            Function6 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

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
            Function7 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })

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
            Function8 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

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
            Function9 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })

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
): Observable<R> = withLatestFrom(other, BiFunction<T, U, R> { t, u -> combiner.invoke(t, u) })

/**
 * Emits a `Pair`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Observable<T>.withLatestFrom(other: ObservableSource<U>): Observable<Pair<T, U>> =
        withLatestFrom(other, BiFunction { t, u -> Pair(t, u) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, R : Any> Observable<T>.withLatestFrom(
        o1: ObservableSource<T1>,
        o2: ObservableSource<T2>,
        crossinline combiner: (T, T1, T2) -> R
): Observable<R> = withLatestFrom(o1, o2, Function3<T, T1, T2, R> { t, t1, t2 -> combiner.invoke(t, t1, t2) })

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, T1 : Any, T2 : Any> Observable<T>.withLatestFrom(
        o1: ObservableSource<T1>,
        o2: ObservableSource<T2>
): Observable<Triple<T, T1, T2>> = withLatestFrom(o1, o2, Function3 { t, t1, t2 -> Triple(t, t1, t2) })

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
): Observable<R> = withLatestFrom(o1, o2, o3, Function4<T, T1, T2, T3, R> { t, t1, t2, t3 -> combiner.invoke(t, t1, t2, t3) })

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
        Function5<T, T1, T2, T3, T4, R> { t, t1, t2, t3, t4 -> combiner.invoke(t, t1, t2, t3, t4) })

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
): Observable<R> = zipWith(other, BiFunction { t, u -> zipper.invoke(t, u) })

/**
 * Emits a zipped `Pair`
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Observable<T>.zipWith(other: ObservableSource<U>): Observable<Pair<T, U>> =
        zipWith(other, BiFunction { t, u -> Pair(t, u) })
