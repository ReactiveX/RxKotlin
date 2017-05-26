package io.reactivex.rxkotlin

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import io.reactivex.functions.Function5
import io.reactivex.functions.Function6
import io.reactivex.functions.Function7
import io.reactivex.functions.Function8
import io.reactivex.functions.Function9

/**
 * SAM adapters to aid Kotlin lambda support
 */
object Observables {

    inline fun <T1, T2, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            crossinline combineFunction: (T1, T2) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, BiFunction<T1, T2, R> { t1, t2 -> combineFunction(t1, t2) })

    inline fun <T1, T2, T3, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, crossinline combineFunction: (T1, T2, T3) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, Function3 { t1: T1, t2: T2, t3: T3 -> combineFunction(t1, t2, t3) })

    inline fun <T1, T2, T3, T4, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            crossinline combineFunction: (T1, T2, T3, T4) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, Function4 { t1: T1, t2: T2, t3: T3, t4: T4 -> combineFunction(t1, t2, t3, t4) })

    inline fun <T1, T2, T3, T4, T5, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, crossinline combineFunction: (T1, T2, T3, T4, T5) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, Function5 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> combineFunction(t1, t2, t3, t4, t5) })

    inline fun <T1, T2, T3, T4, T5, T6, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, Function6 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, Function7 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })


    inline fun <T1, T2, T3, T4, T5, T6, T7, T8, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, Function8 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> combineLatest(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            source9: Observable<T9>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R
    ): Observable<R> = Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, source9, Function9 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8, t9) })


    inline fun <T1, T2, R> zip(source1: Observable<T1>, source2: Observable<T2>, crossinline combineFunction: (T1, T2) -> R): Observable<R> =
            Observable.zip(source1, source2,
                    BiFunction<T1, T2, R> { t1, t2 -> combineFunction(t1, t2) })

    inline fun <T1, T2, T3, R> zip(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>, crossinline combineFunction: (T1, T2, T3) -> R): Observable<R> =
            Observable.zip(source1, source2, source3,
                    Function3 { t1: T1, t2: T2, t3: T3 -> combineFunction(t1, t2, t3) })

    inline fun <T1, T2, T3, T4, R> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>, crossinline combineFunction: (T1, T2, T3, T4) -> R): Observable<R> =
            Observable.zip(source1, source2, source3, source4,
                    Function4 { t1: T1, t2: T2, t3: T3, t4: T4 -> combineFunction(t1, t2, t3, t4) })

    inline fun <T1, T2, T3, T4, T5, R> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, crossinline combineFunction: (T1, T2, T3, T4, T5) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5,
            Function5 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> combineFunction(t1, t2, t3, t4, t5) })


    inline fun <T1, T2, T3, T4, T5, T6, R> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6,
            Function6 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> combineFunction(t1, t2, t3, t4, t5, t6) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, R> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6, source7,
            Function7 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> combineFunction(t1, t2, t3, t4, t5, t6, t7) })


    inline fun <T1, T2, T3, T4, T5, T6, T7, T8, R> zip(
            source1: Observable<T1>, source2: Observable<T2>,
            source3: Observable<T3>, source4: Observable<T4>,
            source5: Observable<T5>, source6: Observable<T6>,
            source7: Observable<T7>, source8: Observable<T8>,
            crossinline combineFunction: (T1, T2, T3, T4, T5, T6, T7, T8) -> R
    ): Observable<R> = Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8,
            Function8 { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> combineFunction(t1, t2, t3, t4, t5, t6, t7, t8) })

    inline fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> zip(
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
inline fun <T, U, R> Observable<T>.withLatestFrom(other: ObservableSource<U>, crossinline combiner: (T, U) -> R): Observable<R>
        = withLatestFrom(other, BiFunction<T, U, R> { t, u -> combiner.invoke(t, u) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, T1, T2, R> Observable<T>.withLatestFrom(o1: ObservableSource<T1>, o2: ObservableSource<T2>, crossinline combiner: (T, T1, T2) -> R): Observable<R>
        = withLatestFrom(o1, o2, Function3<T, T1, T2, R> { t, t1, t2 -> combiner.invoke(t, t1, t2) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, T1, T2, T3, R> Observable<T>.withLatestFrom(o1: ObservableSource<T1>, o2: ObservableSource<T2>, o3: ObservableSource<T3>, crossinline combiner: (T, T1, T2, T3) -> R): Observable<R>
        = withLatestFrom(o1, o2, o3, Function4<T, T1, T2, T3, R> { t, t1, t2, t3 -> combiner.invoke(t, t1, t2, t3) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, T1, T2, T3, T4, R> Observable<T>.withLatestFrom(o1: ObservableSource<T1>, o2: ObservableSource<T2>, o3: ObservableSource<T3>, o4: ObservableSource<T4>, crossinline combiner: (T, T1, T2, T3, T4) -> R): Observable<R>
        = withLatestFrom(o1, o2, o3, o4, Function5<T, T1, T2, T3, T4, R> { t, t1, t2, t3, t4 -> combiner.invoke(t, t1, t2, t3, t4) })

/**
 * Combine latest operator that produces [Pair]
 */
inline fun <T1 : Any, T2 : Any, R : Any> Observable<T1>.combineLatestWith(observable: Observable<T2>, crossinline combiner: (T1, T2) -> R): Observable<R>
        = Observable.combineLatest(this, observable, BiFunction { t1, t2 -> combiner.invoke(t1, t2) })

/**
 * Combine latest operator that produces [Triple]
 */
inline fun <T1 : Any, T2 : Any, T3 : Any, R : Any> Observable<T1>.combineLatestWith(observable1: Observable<T2>, observable2: Observable<T3>, crossinline combiner: (T1, T2, T3) -> R): Observable<R>
        = Observable.combineLatest(this, observable1, observable2, Function3 { t1, t2, t3 -> combiner.invoke(t1, t2, t3) })

/**
 * Combine latest operator that produces [Pair]
 */
fun <T : Any, R : Any> Observable<T>.combineLatestWith(observable: Observable<R>): Observable<Pair<T, R>>
        = Observable.combineLatest(this, observable, BiFunction(::Pair))

/**
 * Combine latest operator that produces [Triple]
 */
fun <T : Any, R : Any, U : Any> Observable<T>.combineLatestWith(observable1: Observable<R>, observable2: Observable<U>): Observable<Triple<T, R, U>>
        = Observable.combineLatest(this, observable1, observable2, Function3(::Triple))

/**
 * Zip operator that produces [Pair]
 */
fun <T : Any, R : Any> Observable<T>.zipWith(observable: Observable<R>): Observable<Pair<T, R>>
        = Observable.zip(this, observable, BiFunction(::Pair))

/**
 * Zip operator that produces [Triple]
 */
fun <T : Any, R : Any, U : Any> Observable<T>.zipWith(observable1: Observable<R>, observable2: Observable<U>): Observable<Triple<T, R, U>>
        = Observable.zip(this, observable1, observable2, Function3(::Triple))

/**
 * An alias to [Observable.zipWith], but allowing for cleaner lambda syntax.
 */
inline fun <T, U, R> Observable<T>.zipWith(other: ObservableSource<U>, crossinline zipper: (T, U) -> R): Observable<R>
        = Observable.zip(this, other, BiFunction { t, u -> zipper.invoke(t, u) })

/**
 * An alias to [Observable.zipWith], but allowing for cleaner lambda syntax.
 */
inline fun <T, U, E, R> Observable<T>.zipWith(other: ObservableSource<U>, other2: ObservableSource<E>, crossinline zipper: (T, U, E) -> R): Observable<R>
        = Observable.zip(this, other, other2, Function3 { t, u, e -> zipper.invoke(t, u, e) })
