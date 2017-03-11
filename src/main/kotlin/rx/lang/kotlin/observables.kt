package rx.lang.kotlin

import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.observables.BlockingObservable

fun <T> emptyObservable() : Observable<T> = Observable.empty()
fun <T> observable(body : (s : Subscriber<in T>) -> Unit) : Observable<T> = Observable.create(body)
/**
 * Create deferred observable
 * @see [rx.Observable.defer] and [http://reactivex.io/documentation/operators/defer.html]
 */
fun <T> deferredObservable(body : () -> Observable<T>) : Observable<T> = Observable.defer(body)
private fun <T> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

fun BooleanArray.toObservable() : Observable<Boolean> = this.toList().toObservable()
fun ByteArray.toObservable() : Observable<Byte> = this.toList().toObservable()
fun ShortArray.toObservable() : Observable<Short> = this.toList().toObservable()
fun IntArray.toObservable() : Observable<Int> = this.toList().toObservable()
fun LongArray.toObservable() : Observable<Long> = this.toList().toObservable()
fun FloatArray.toObservable() : Observable<Float> = this.toList().toObservable()
fun DoubleArray.toObservable() : Observable<Double> = this.toList().toObservable()
fun <T> Array<out T>.toObservable() : Observable<T> = Observable.from(this)

fun IntProgression.toObservable() : Observable<Int> =
        if (step == 1 && last.toLong() - first < Integer.MAX_VALUE) Observable.range(first, Math.max(0, last - first + 1))
        else Observable.from(this)

fun <T> Iterator<T>.toObservable() : Observable<T> = toIterable().toObservable()
fun <T> Iterable<T>.toObservable() : Observable<T> = Observable.from(this)
fun <T> Sequence<T>.toObservable() : Observable<T> = Observable.from(object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toObservable.iterator()
})

fun <T> T.toSingletonObservable() : Observable<T> = Observable.just(this)
fun <T> Throwable.toObservable() : Observable<T> = Observable.error(this)

fun <T> Iterable<Observable<out T>>.merge() : Observable<T> = Observable.merge(this.toObservable())
fun <T> Iterable<Observable<out T>>.mergeDelayError() : Observable<T> = Observable.mergeDelayError(this.toObservable())


fun <T, R> Observable<T>.fold(initial : R, body : (R, T) -> R) : Observable<R> = reduce(initial, {a, e -> body(a, e)})
fun <T> Observable<T>.onError(block : (Throwable) -> Unit) : Observable<T> = doOnError(block)
@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND") fun <T> Observable<T>.firstOrNull() : Observable<T?> = firstOrDefault(null)
fun <T> BlockingObservable<T>.firstOrNull() : T = firstOrDefault(null)

@Suppress("BASE_WITH_NULLABLE_UPPER_BOUND") fun <T> Observable<T>.onErrorReturnNull() : Observable<T?> = onErrorReturn<T> {null}

fun <T, R> Observable<T>.lift(operator : (Subscriber<in R>) -> Subscriber<T>) : Observable<R> = lift { t1 -> operator(t1!!) }

/**
 * Returns [Observable] that requires all objects to be non null. Raising [NullPointerException] in case of null object
 */
fun <T : Any> Observable<T?>.requireNoNulls() : Observable<T> = map { it ?: throw NullPointerException("null element found in rx observable") }

/**
 * Returns [Observable] with non-null generic type T. Returned observable filter out null values
 */
@Suppress("CAST_NEVER_SUCCEEDS") fun <T : Any> Observable<T?>.filterNotNull(): Observable<T> = filter { it != null } as Observable<T>

/**
 * Returns Observable that wrap all values into [IndexedValue] and populates corresponding index value.
 * Works similar to [kotlin.withIndex]
 */
fun <T> Observable<T>.withIndex() : Observable<IndexedValue<T>> =
        zipWith(Observable.range(0, Int.MAX_VALUE)) { value, index -> IndexedValue(index,value) }

/**
 * Returns Observable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one observable.
 * Works similar to [Observable.flatMap] and [Observable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source observable that returns [Sequence]
 *  @returns Observable that merges all [Sequence]s produced by [body] functions
 */
fun <T, R> Observable<T>.flatMapSequence( body : (T) -> Sequence<R> ) : Observable<R> = flatMap { body(it).toObservable() }

/**
 * Subscribe with a subscriber that is configured inside body
 */
inline fun <T> Observable<T>.subscribeWith( body : FunctionSubscriberModifier<T>.() -> Unit ) : Subscription {
    val modifier = FunctionSubscriberModifier(subscriber<T>())
    modifier.body()
    return subscribe(modifier.subscriber)
}

fun <T> Observable<Observable<T>>.switchOnNext(): Observable<T> = Observable.switchOnNext(this)

/**
 * Observable.combineLatest(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
fun <T, R> List<Observable<T>>.combineLatest(combineFunction: (args: List<T>) -> R): Observable<R> =
        Observable.combineLatest(this, { combineFunction(it.asList() as List<T>) })

/**
 * Observable.zip(List<? extends Observable<? extends T>> sources, FuncN<? extends R> combineFunction)
 */
@Suppress("UNCHECKED_CAST")
fun <T, R> List<Observable<T>>.zip(zipFunction: (args: List<T>) -> R): Observable<R> =
        Observable.zip(this, { zipFunction(it.asList() as List<T>) })@Suppress("UNCHECKED_CAST")

fun <T, R> List<Observable<T>>.flatZip(zipFunction: (args: List<T>) -> Observable<R>): Observable<R> =
        Observable.zip(this, { zipFunction(it.asList() as List<T>) }).flatMap { it }

fun <T, T1, R> Observable<T>.flatZipWith(o1: Observable<out T1>, zipFunction: (t: T, o1: T1) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, zipFunction).flatMap { it }

fun <T, T1, T2, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, zipFunction: (t: T, o1: T1, o2: T2) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, zipFunction).flatMap { it }

fun <T, T1, T2, T3, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, o3: Observable<out T3>, zipFunction: (t: T, o1: T1, o2: T2, o3: T3) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, o3, zipFunction).flatMap { it }

fun <T, T1, T2, T3, T4, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, o3: Observable<out T3>, o4: Observable<out T4>, zipFunction: (t: T, o1: T1, o2: T2, o3: T3, o4: T4) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, o3, o4, zipFunction).flatMap { it }

fun <T, T1, T2, T3, T4, T5, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, o3: Observable<out T3>, o4: Observable<out T4>, o5: Observable<out T5>, zipFunction: (t: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, o3, o4, o5, zipFunction).flatMap { it }

fun <T, T1, T2, T3, T4, T5, T6, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, o3: Observable<out T3>, o4: Observable<out T4>, o5: Observable<out T5>, o6: Observable<out T6>, zipFunction: (t: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, o3, o4, o5, o6, zipFunction).flatMap { it }

fun <T, T1, T2, T3, T4, T5, T6, T7, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, o3: Observable<out T3>, o4: Observable<out T4>, o5: Observable<out T5>, o6: Observable<out T6>, o7: Observable<T7>, zipFunction: (t: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, o3, o4, o5, o6, o7, zipFunction).flatMap { it }

fun <T, T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<T>.flatZipWith(o1: Observable<out T1>, o2: Observable<out T2>, o3: Observable<out T3>, o4: Observable<out T4>, o5: Observable<out T5>, o6: Observable<out T6>, o7: Observable<T7>, o8: Observable<T8>, zipFunction: (t: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8) -> Observable<R>): Observable<R> =
        Observable.zip(this, o1, o2, o3, o4, o5, o6, o7, o8, zipFunction).flatMap { it }

/**
 * Returns an Observable that emits the items emitted by the source Observable, converted to the specified type.
 */
inline fun <reified R : Any> Observable<*>.cast(): Observable<R> = cast(R::class.java)

/**
 * Filters the items emitted by an Observable, only emitting those of the specified type.
 */
inline fun <reified R : Any> Observable<*>.ofType(): Observable<R> = ofType(R::class.java)
