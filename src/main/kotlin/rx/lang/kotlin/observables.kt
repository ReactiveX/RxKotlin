package rx.lang.kotlin

import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.observables.BlockingObservable

public fun <T> emptyObservable() : Observable<T> = Observable.empty()
public fun <T> observable(body : (s : Subscriber<in T>) -> Unit) : Observable<T> = Observable.create(body)
/**
 * Create deferred observable
 * @see [rx.Observable.defer] and [http://reactivex.io/documentation/operators/defer.html]
 */
public fun <T> deferredObservable(body : () -> Observable<T>) : Observable<T> = Observable.defer(body)
private fun <T> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

public fun BooleanArray.toObservable() : Observable<Boolean> = this.toList().toObservable()
public fun ByteArray.toObservable() : Observable<Byte> = this.toList().toObservable()
public fun ShortArray.toObservable() : Observable<Short> = this.toList().toObservable()
public fun IntArray.toObservable() : Observable<Int> = this.toList().toObservable()
public fun LongArray.toObservable() : Observable<Long> = this.toList().toObservable()
public fun FloatArray.toObservable() : Observable<Float> = this.toList().toObservable()
public fun DoubleArray.toObservable() : Observable<Double> = this.toList().toObservable()
public fun <T> Array<out T>.toObservable() : Observable<T> = Observable.from(this)

public fun Progression<Int>.toObservable() : Observable<Int> =
        if (increment == 1 && end.toLong() - start < Integer.MAX_VALUE) Observable.range(start, Math.max(0, end - start + 1))
        else Observable.from(this)

public fun <T> Iterator<T>.toObservable() : Observable<T> = toIterable().toObservable()
public fun <T> Iterable<T>.toObservable() : Observable<T> = Observable.from(this)
public fun <T> Sequence<T>.toObservable() : Observable<T> = Observable.from(object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toObservable.iterator()
})

public fun <T> T.toSingletonObservable() : Observable<T> = Observable.just(this)
public fun <T> Throwable.toObservable() : Observable<T> = Observable.error(this)

public fun <T> Iterable<Observable<out T>>.merge() : Observable<T> = Observable.merge(this.toObservable())
public fun <T> Iterable<Observable<out T>>.mergeDelayError() : Observable<T> = Observable.mergeDelayError(this.toObservable())


public fun <T, R> Observable<T>.fold(initial : R, body : (R, T) -> R) : Observable<R> = reduce(initial, {a, e -> body(a, e)})
public fun <T> Observable<T>.onError(block : (Throwable) -> Unit) : Observable<T> = doOnError(block)
@suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
public fun <T> Observable<T>.firstOrNull() : Observable<T?> = firstOrDefault(null)
public fun <T> BlockingObservable<T>.firstOrNull() : T = firstOrDefault(null)

@suppress("BASE_WITH_NULLABLE_UPPER_BOUND")
public fun <T> Observable<T>.onErrorReturnNull() : Observable<T?> = onErrorReturn {null}

public fun <T, R> Observable<T>.lift(operator : (Subscriber<in R>) -> Subscriber<T>) : Observable<R> = lift(object : Observable.Operator<R, T> {
    override fun call(t1: Subscriber<in R>?): Subscriber<in T> = operator(t1!!)
})

/**
 * Returns [Observable] that requires all objects to be non null. Raising [NullPointerException] in case of null object
 */
public fun <T : Any> Observable<T?>.requireNoNulls() : Observable<T> = lift { s ->
    subscriber<T?>().
            onCompleted { s.onCompleted() }.
            onError { t -> s.onError(t) }.
            onNext { v -> if (v == null) throw NullPointerException("null element found in rx observable") else s.onNext(v) }
}

/**
 * Returns [Observable] with non-null generic type T. Returned observable filter out null values
 */
public fun <T : Any> Observable<T?>.filterNotNull() : Observable<T> = lift { s ->
    subscriber<T?>().
            onCompleted { s.onCompleted() }.
            onError { t -> s.onError(t) }.
            onNext { v -> if (v != null) s.onNext(v) }
}

/**
 * Returns Observable that wrap all values into [IndexedValue] and populates corresponding index value.
 * Works similar to [kotlin.withIndex]
 */
public fun <T> Observable<T>.withIndex() : Observable<IndexedValue<T>> = lift { s ->
    var index = 0

    subscriber<T>().
            onNext { v -> s.onNext(IndexedValue(index++, v)) }.
            onCompleted { s.onCompleted() }.
            onError { t -> s.onError(t) }
}

/**
 * Returns Observable that emits objects from kotlin [Sequence] returned by function you provided by parameter [body] for
 * each input object and merges all produced elements into one observable.
 * Works similar to [Observable.flatMap] and [Observable.flatMapIterable] but with [Sequence]
 *
 * @param body is a function that applied for each item emitted by source observable that returns [Sequence]
 *  @returns Observable that merges all [Sequence]s produced by [body] functions
 */
public fun <T, R> Observable<T>.flatMapSequence( body : (T) -> Sequence<R> ) : Observable<R> = flatMap { body(it).toObservable() }

/**
 * Subscribe with a subscriber that is configured inside body
 */
public inline fun <T> Observable<T>.subscribeWith( body : FunctionSubscriberModifier<T>.() -> Unit ) : Subscription {
    val modifier = FunctionSubscriberModifier(subscriber<T>())
    modifier.body()
    return subscribe(modifier.subscriber)
}
