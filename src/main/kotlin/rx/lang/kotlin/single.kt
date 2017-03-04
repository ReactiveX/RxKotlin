package rx.lang.kotlin

import rx.Single
import rx.SingleSubscriber
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T> T.toSingle(): Single<T> = Single.just(this)
fun <T> Future<T>.toSingle(): Single<T> = Single.from(this)
fun <T> Callable<T>.toSingle(): Single<T> = Single.fromCallable { this.call() }
