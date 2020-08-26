@file:Suppress("unused")

package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.annotations.BackpressureKind
import io.reactivex.rxjava3.annotations.BackpressureSupport
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.functions.*
import org.reactivestreams.Publisher


object Flowables {

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            crossinline combineFunction: (T1, T2) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2,
            { t1, t2 -> combineFunction(t1, t2) })

    /**
     * Emits `Pair<T1,T2>`
     */
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>
    ): Flowable<Pair<T1, T2>> = Flowable.combineLatest(source1, source2,
            { t1, t2 -> t1 to t2 })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            crossinline combineFunction: (T1, T2, T3) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3,
            { t1, t2, t3 -> combineFunction(t1, t2, t3) })

    /**
     * Emits `Triple<T1,T2,T3>`
     */
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any, T3 : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>
    ): Flowable<Triple<T1, T2, T3>> = Flowable.combineLatest(source1, source2, source3,
            { t1, t2, t3 -> Triple(t1, t2, t3) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, source4, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            crossinline combineFunction: (T1, T2, T3, T4) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3, source4,
            { t1, t2, t3, t4 -> combineFunction(t1, t2, t3, t4) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, source4, source5, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            source5: Flowable<T5>,
            crossinline combineFunction: (T1, T2, T3, T4, T5) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3, source4, source5,
            { t1, t2, t3, t4, t5 -> combineFunction(t1, t2, t3, t4, t5) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, source4, source5, source6, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            source5: Flowable<T5>,
            source6: Flowable<T6>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3, source4, source5, source6,
            { t1, t2, t3, t4, t5, t6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, source4, source5, source6, source7, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            source5: Flowable<T5>,
            source6: Flowable<T6>,
            source7: Flowable<T7>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3, source4, source5, source6, source7,
            { t1, t2, t3, t4, t5, t6, t7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            source5: Flowable<T5>,
            source6: Flowable<T6>,
            source7: Flowable<T7>,
            source8: Flowable<T8>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8,
            { t1, t2, t3, t4, t5, t6, t7, t8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, source9, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, R : Any> combineLatest(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            source5: Flowable<T5>,
            source6: Flowable<T6>,
            source7: Flowable<T7>,
            source8: Flowable<T8>,
            source9: Flowable<T9>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Flowable<R> = Flowable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, source9,
            { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })


    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.SPECIAL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T : Any> create(
            mode: BackpressureStrategy,
            crossinline source: (FlowableEmitter<T>) -> Unit
    ): Flowable<T> = Flowable.create({ source(it) }, mode)

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, R : Any> zip(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            crossinline combineFunction: (T1, T2) -> R
    ): Flowable<R> = Flowable.zip(source1, source2,
            { t1, t2 -> combineFunction(t1, t2) })

    /**
     * Emits `Pair<T1,T2>`
     */
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any> zip(source1: Flowable<T1>, source2: Flowable<T2>): Flowable<Pair<T1, T2>> =
            Flowable.zip(source1, source2, { t1, t2 -> t1 to t2 })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> zip(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            crossinline combineFunction: (T1, T2, T3) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3,
            { t1, t2, t3 -> combineFunction(t1, t2, t3) })

    /**
     * Emits `Triple<T1,T2,T3>`
     */
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    fun <T1 : Any, T2 : Any, T3 : Any> zip(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>
    ): Flowable<Triple<T1, T2, T3>> = Flowable.zip(source1, source2, source3,
            { t1, t2, t3 -> Triple(t1, t2, t3) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, source4, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> zip(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            crossinline combineFunction: (T1, T2, T3, T4) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3, source4,
            { t1, t2, t3, t4 -> combineFunction(t1, t2, t3, t4) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, source4, source5, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> zip(
            source1: Flowable<T1>, source2: Flowable<T2>,
            source3: Flowable<T3>, source4: Flowable<T4>,
            source5: Flowable<T5>, crossinline combineFunction: (T1, T2, T3, T4, T5) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3, source4, source5,
            { t1, t2, t3, t4, t5 -> combineFunction(t1, t2, t3, t4, t5) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, source4, source5, source6, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, R : Any> zip(
            source1: Flowable<T1>, source2: Flowable<T2>,
            source3: Flowable<T3>, source4: Flowable<T4>,
            source5: Flowable<T5>, source6: Flowable<T6>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3, source4, source5, source6,
            { t1, t2, t3, t4, t5, t6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, source4, source5, source6, source7, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, R : Any> zip(
            source1: Flowable<T1>, source2: Flowable<T2>,
            source3: Flowable<T3>, source4: Flowable<T4>,
            source5: Flowable<T5>, source6: Flowable<T6>,
            source7: Flowable<T7>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3, source4, source5, source6, source7,
            { t1, t2, t3, t4, t5, t6, t7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })


    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, source4, source5, source6, source7, source8, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, R : Any> zip(
            source1: Flowable<T1>, source2: Flowable<T2>,
            source3: Flowable<T3>, source4: Flowable<T4>,
            source5: Flowable<T5>, source6: Flowable<T6>,
            source7: Flowable<T7>, source8: Flowable<T8>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3, source4, source5, source6, source7, source8,
            { t1, t2, t3, t4, t5, t6, t7, t8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

    @Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
            replaceWith = ReplaceWith("Flowable.zip(source1, source2, source3, source4, source5, source6, source7, source8, source9, combineFunction)", "io.reactivex.Flowable"),
            level = DeprecationLevel.WARNING)
    @CheckReturnValue
    @BackpressureSupport(BackpressureKind.FULL)
    @SchedulerSupport(SchedulerSupport.NONE)
    inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, R : Any> zip(
            source1: Flowable<T1>,
            source2: Flowable<T2>,
            source3: Flowable<T3>,
            source4: Flowable<T4>,
            source5: Flowable<T5>,
            source6: Flowable<T6>,
            source7: Flowable<T7>,
            source8: Flowable<T8>,
            source9: Flowable<T9>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Flowable<R> = Flowable.zip(source1, source2, source3, source4, source5, source6, source7, source8, source9,
            { t1, t2, t3, t4, t5, t6, t7, t8, t9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })

}

/**
 * An alias to [Flowable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(other, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, U : Any, R : Any> Flowable<T>.withLatestFrom(
        other: Publisher<U>,
        crossinline combiner: (T, U) -> R
): Flowable<R> = withLatestFrom(other, { t, u -> combiner(t, u) })

@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Flowable<T>.withLatestFrom(other: Publisher<U>): Flowable<Pair<T, U>> =
        withLatestFrom(other, { t, u -> Pair(t, u) })


/**
 * An alias to [Flowable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, R : Any> Flowable<T>.withLatestFrom(
        o1: Publisher<T1>,
        o2: Publisher<T2>,
        crossinline combiner: (T, T1, T2) -> R
): Flowable<R> = withLatestFrom(o1, o2, { t, t1, t2 -> combiner(t, t1, t2) })

@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, T1 : Any, T2 : Any> Flowable<T>.withLatestFrom(
        o1: Publisher<T1>,
        o2: Publisher<T2>
): Flowable<Triple<T, T1, T2>> = withLatestFrom(o1, o2, { t, t1, t2 -> Triple(t, t1, t2) })

/**
 * An alias to [Flowable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, o3, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, T3 : Any, R : Any> Flowable<T>.withLatestFrom(
        o1: Publisher<T1>,
        o2: Publisher<T2>,
        o3: Publisher<T3>,
        crossinline combiner: (T, T1, T2, T3) -> R
): Flowable<R> = withLatestFrom(o1, o2, o3, { t, t1, t2, t3 -> combiner(t, t1, t2, t3) })

/**
 * An alias to [Flowable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("withLatestFrom(o1, o2, o3, o4, combiner)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> Flowable<T>.withLatestFrom(
        o1: Publisher<T1>,
        o2: Publisher<T2>,
        o3: Publisher<T3>,
        o4: Publisher<T4>,
        crossinline combiner: (T, T1, T2, T3, T4) -> R
): Flowable<R> = withLatestFrom(o1, o2, o3, o4, { t, t1, t2, t3, t4 -> combiner(t, t1, t2, t3, t4) })

/**
 * An alias to [Flowable.zipWith], but allowing for cleaner lambda syntax.
 */
@Deprecated("New type inference algorithm in Kotlin 1.4 makes this method obsolete. Method will be removed in future RxKotlin release.",
        replaceWith = ReplaceWith("zipWith(other, zipper)"),
        level = DeprecationLevel.WARNING)
@CheckReturnValue
@BackpressureSupport(BackpressureKind.PASS_THROUGH)
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <T : Any, U : Any, R : Any> Flowable<T>.zipWith(
        other: Publisher<U>,
        crossinline zipper: (T, U) -> R
): Flowable<R> = zipWith(other, { t, u -> zipper(t, u) })

/**
 * Emits a zipped `Pair`
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Flowable<T>.zipWith(other: Publisher<U>): Flowable<Pair<T, U>> =
        zipWith(other, { t, u -> Pair(t, u) })
