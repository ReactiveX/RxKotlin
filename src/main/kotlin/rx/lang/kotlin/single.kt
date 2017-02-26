package rx.lang.kotlin

import rx.Single
import rx.SingleSubscriber
import java.util.concurrent.Callable
import java.util.concurrent.Future

inline fun <T> single(crossinline body: (s: SingleSubscriber<in T>) -> Unit): Single<T> = Single.create { body(it) }
fun <T> T.toSingle(): Single<T> = Single.just(this)
fun <T> Future<T>.toSingle(): Single<T> = Single.from(this)
fun <T> Callable<T>.toSingle(): Single<T> = Single.fromCallable { this.call() }
