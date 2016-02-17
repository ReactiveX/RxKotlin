package rx.lang.kotlin

import rx.Single
import rx.SingleSubscriber
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T> single(body: (s: SingleSubscriber<in T>) -> Unit): Single<T> = Single.create(body)
fun <T> singleOf(value: T): Single<T> = Single.just(value)
fun <T> Future<T>.toSingle(): Single<T> = Single.from(this)
fun <T> Callable<T>.toSingle(): Single<T> = Single.fromCallable { this.call() }