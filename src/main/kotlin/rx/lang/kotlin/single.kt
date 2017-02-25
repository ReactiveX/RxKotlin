package rx.lang.kotlin

import rx.Single
import rx.SingleSubscriber
import rx.Subscription
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T> T.toSingle(): Single<T> = Single.just(this)
fun <T> Future<T>.toSingle(): Single<T> = Single.from(this)
fun <T> Callable<T>.toSingle(): Single<T> = Single.fromCallable { this.call() }
fun <T> Throwable.toSingle(): Single<T> = Single.error(this)

/**
 * Subscribe with a subscriber that is configured inside body
 */
inline fun <T> Single<T>.subscribeWith(body: FunctionSingleSubscriberModifier<T>.() -> Unit): Subscription {
    val modifier = FunctionSingleSubscriberModifier(FunctionSingleSubscriber<T>())
    modifier.body()
    return subscribe(modifier.subscriber)
}
