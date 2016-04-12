package rx.lang.kotlin

import rx.Single
import rx.SingleSubscriber
import rx.Subscription
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T> single(body: (s: SingleSubscriber<in T>) -> Unit): Single<T> = Single.create(body)
fun <T> singleOf(value: T): Single<T> = Single.just(value)
fun <T> Future<T>.toSingle(): Single<T> = Single.from(this)
fun <T> Callable<T>.toSingle(): Single<T> = Single.fromCallable { this.call() }
fun <T> Throwable.toSingle(): Single<T> = Single.error(this)

/**
 * Subscribe with a subscriber that is configured inside body
 */
inline fun <T> Single<T>.subscribeWith(body: FunctionSingleSubscriberModifier<T>.() -> Unit): Subscription {
    val modifier = FunctionSingleSubscriberModifier(singleSubscriber<T>())
    modifier.body()
    return subscribe(modifier.subscriber)
}
