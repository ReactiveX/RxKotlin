package io.reactivex.rxkotlin

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.BiFunction

/**
 * SAM adapters to aid Kotlin lambda support
 */
object Observables {

    inline fun <T1,T2,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>, crossinline combiner: (T1, T2) -> R) =
            Observable.combineLatest(source1, source2,
                    BiFunction<T1, T2, R> { t1, t2 -> combiner(t1,t2) })!!

    inline fun <T1,T2,T3,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>, crossinline combiner: (T1,T2, T3) -> R) =
            Observable.combineLatest(source1, source2,source3,
                    io.reactivex.functions.Function3<T1, T2, T3, R> { t1: T1, t2: T2, t3: T3 -> combiner(t1,t2, t3) })!!

    inline fun <T1,T2,T3,T4,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>,
                                             source4: Observable<T4>, crossinline combiner: (T1,T2, T3, T4) -> R) =
            Observable.combineLatest(source1, source2,source3, source4,
                    io.reactivex.functions.Function4<T1, T2, T3, T4, R> { t1: T1, t2: T2, t3: T3, t4: T4 -> combiner(t1,t2, t3, t4) })!!


    inline fun <T1,T2,T3,T4,T5,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>,
                                                source3: Observable<T3>, source4: Observable<T4>,
                                                source5: Observable<T5>, crossinline combiner: (T1,T2, T3, T4, T5) -> R) =
            Observable.combineLatest(source1, source2,source3, source4, source5,
                    io.reactivex.functions.Function5<T1, T2, T3, T4, T5, R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> combiner(t1,t2, t3, t4, t5) })!!


    inline fun <T1,T2,T3,T4,T5,T6,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>,
                                                source3: Observable<T3>, source4: Observable<T4>,
                                                source5: Observable<T5>, source6: Observable<T6>, crossinline combiner: (T1,T2, T3, T4, T5, T6) -> R) =
            Observable.combineLatest(source1, source2,source3, source4, source5, source6,
                    io.reactivex.functions.Function6<T1, T2, T3, T4, T5, T6, R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> combiner(t1,t2, t3, t4, t5, t6) })!!

    inline fun <T1,T2,T3,T4,T5,T6,T7,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>,
                                                   source3: Observable<T3>, source4: Observable<T4>,
                                                   source5: Observable<T5>, source6: Observable<T6>,
                                                      source7: Observable<T7>, crossinline combiner: (T1,T2, T3, T4, T5, T6, T7) -> R) =
            Observable.combineLatest(source1, source2,source3, source4, source5, source6, source7,
                    io.reactivex.functions.Function7<T1, T2, T3, T4, T5, T6, T7, R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> combiner(t1,t2, t3, t4, t5, t6, t7) })!!


    inline fun <T1,T2,T3,T4,T5,T6,T7,T8,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>,
                                                      source3: Observable<T3>, source4: Observable<T4>,
                                                      source5: Observable<T5>, source6: Observable<T6>,
                                                      source7: Observable<T7>, source8: Observable<T8>,
                                                         crossinline combiner: (T1,T2, T3, T4, T5, T6, T7, T8) -> R) =
            Observable.combineLatest(source1, source2,source3, source4, source5, source6, source7, source8,
                    io.reactivex.functions.Function8<T1, T2, T3, T4, T5, T6, T7, T8,R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> combiner(t1,t2, t3, t4, t5, t6, t7, t8) })!!

    inline fun <T1,T2,T3,T4,T5,T6,T7,T8,T9,R> combineLatest(source1: Observable<T1>, source2: Observable<T2>,
                                                         source3: Observable<T3>, source4: Observable<T4>,
                                                         source5: Observable<T5>, source6: Observable<T6>,
                                                         source7: Observable<T7>, source8: Observable<T8>,
                                                         source9: Observable<T9>, crossinline combiner: (T1,T2, T3, T4, T5, T6, T7, T8, T9) -> R) =
            Observable.combineLatest(source1, source2,source3, source4, source5, source6, source7, source8, source9,
                    io.reactivex.functions.Function9<T1, T2, T3, T4, T5, T6, T7, T8,T9,R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 -> combiner(t1,t2, t3, t4, t5, t6, t7, t8, t9) })!!


    
    
    inline fun <T1,T2,R> zip(source1: Observable<T1>, source2: Observable<T2>, crossinline combiner: (T1, T2) -> R) =
            Observable.zip(source1, source2,
                    BiFunction<T1, T2, R> { t1, t2 -> combiner(t1,t2) })!!

    inline fun <T1,T2,T3,R> zip(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>, crossinline combiner: (T1,T2, T3) -> R) =
            Observable.zip(source1, source2,source3,
                    io.reactivex.functions.Function3<T1, T2, T3, R> { t1: T1, t2: T2, t3: T3 -> combiner(t1,t2, t3) })!!

    inline fun <T1,T2,T3,T4,R> zip(source1: Observable<T1>, source2: Observable<T2>, source3: Observable<T3>, source4: Observable<T4>, crossinline combiner: (T1,T2, T3, T4) -> R) =
            Observable.zip(source1, source2,source3, source4,
                    io.reactivex.functions.Function4<T1, T2, T3, T4, R> { t1: T1, t2: T2, t3: T3, t4: T4 -> combiner(t1,t2, t3, t4) })!!

    inline fun <T1,T2,T3,T4,T5,R> zip(source1: Observable<T1>, source2: Observable<T2>,
                                      source3: Observable<T3>, source4: Observable<T4>,
                                      source5: Observable<T5>, crossinline combiner: (T1,T2, T3, T4, T5) -> R) =
            Observable.zip(source1, source2,source3, source4, source5,
                    io.reactivex.functions.Function5<T1, T2, T3, T4, T5, R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5 -> combiner(t1,t2, t3, t4, t5) })!!



    inline fun <T1,T2,T3,T4,T5,T6,R> zip(source1: Observable<T1>, source2: Observable<T2>,
                                                   source3: Observable<T3>, source4: Observable<T4>,
                                                   source5: Observable<T5>, source6: Observable<T6>, crossinline combiner: (T1,T2, T3, T4, T5, T6) -> R) =
            Observable.zip(source1, source2,source3, source4, source5, source6,
                    io.reactivex.functions.Function6<T1, T2, T3, T4, T5, T6, R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6 -> combiner(t1,t2, t3, t4, t5, t6) })!!

    inline fun <T1,T2,T3,T4,T5,T6,T7,R> zip(source1: Observable<T1>, source2: Observable<T2>,
                                                      source3: Observable<T3>, source4: Observable<T4>,
                                                      source5: Observable<T5>, source6: Observable<T6>,
                                                      source7: Observable<T7>, crossinline combiner: (T1,T2, T3, T4, T5, T6, T7) -> R) =
            Observable.zip(source1, source2,source3, source4, source5, source6, source7,
                    io.reactivex.functions.Function7<T1, T2, T3, T4, T5, T6, T7, R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7 -> combiner(t1,t2, t3, t4, t5, t6, t7) })!!


    inline fun <T1,T2,T3,T4,T5,T6,T7,T8,R> zip(source1: Observable<T1>, source2: Observable<T2>,
                                                         source3: Observable<T3>, source4: Observable<T4>,
                                                         source5: Observable<T5>, source6: Observable<T6>,
                                                         source7: Observable<T7>, source8: Observable<T8>,
                                                         crossinline combiner: (T1,T2, T3, T4, T5, T6, T7, T8) -> R) =
            Observable.zip(source1, source2,source3, source4, source5, source6, source7, source8,
                    io.reactivex.functions.Function8<T1, T2, T3, T4, T5, T6, T7, T8,R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8 -> combiner(t1,t2, t3, t4, t5, t6, t7, t8) })!!

    inline fun <T1,T2,T3,T4,T5,T6,T7,T8,T9,R> zip(source1: Observable<T1>, source2: Observable<T2>,
                                                            source3: Observable<T3>, source4: Observable<T4>,
                                                            source5: Observable<T5>, source6: Observable<T6>,
                                                            source7: Observable<T7>, source8: Observable<T8>,
                                                            source9: Observable<T9>, crossinline combiner: (T1,T2, T3, T4, T5, T6, T7, T8, T9) -> R) =
            Observable.zip(source1, source2,source3, source4, source5, source6, source7, source8, source9,
                    io.reactivex.functions.Function9<T1, T2, T3, T4, T5, T6, T7, T8,T9,R> { t1: T1, t2: T2, t3: T3, t4: T4, t5: T5, t6: T6, t7: T7, t8: T8, t9: T9 -> combiner(t1,t2, t3, t4, t5, t6, t7, t8, t9) })!!

}


/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, U, R> Observable<T>.withLatestFrom(other: ObservableSource<U>, crossinline combiner: (T, U) -> R): Observable<R>
        = withLatestFrom(other, BiFunction<T, U, R> { t, u -> combiner.invoke(t, u)  })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, T1, T2, R> Observable<T>.withLatestFrom(o1: ObservableSource<T1>, o2: ObservableSource<T2>, crossinline combiner: (T, T1, T2) -> R): Observable<R>
        = withLatestFrom(o1, o2, io.reactivex.functions.Function3<T, T1, T2, R> { t, t1, t2 -> combiner.invoke(t, t1, t2) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, T1, T2, T3, R> Observable<T>.withLatestFrom(o1: ObservableSource<T1>, o2: ObservableSource<T2>, o3: ObservableSource<T3>, crossinline combiner: (T, T1, T2, T3) -> R): Observable<R>
        = withLatestFrom(o1, o2, o3, io.reactivex.functions.Function4<T, T1, T2, T3, R> { t, t1, t2, t3 -> combiner.invoke(t, t1, t2, t3) })

/**
 * An alias to [Observable.withLatestFrom], but allowing for cleaner lambda syntax.
 */
inline fun <T, T1, T2, T3, T4, R> Observable<T>.withLatestFrom(o1: ObservableSource<T1>, o2: ObservableSource<T2>, o3: ObservableSource<T3>, o4: ObservableSource<T4>, crossinline combiner: (T, T1, T2, T3, T4) -> R): Observable<R>
        = withLatestFrom(o1, o2, o3, o4, io.reactivex.functions.Function5<T, T1, T2, T3, T4, R> { t, t1, t2, t3, t4 -> combiner.invoke(t, t1, t2, t3, t4) })

/**
 * An alias to [Observable.zipWith], but allowing for cleaner lambda syntax.
 */
inline fun <T, U, R> Observable<T>.zipWith(other: ObservableSource<U>, crossinline zipper: (T, U) -> R): Observable<R>
        = zipWith(other, BiFunction { t, u -> zipper.invoke(t, u) })

