package rx.lang.kotlin

import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.util.concurrent.Callable
import java.util.concurrent.Future

inline fun <T : Any> single(crossinline body: (s: SingleEmitter<in T>) -> Unit): Single<T> = Single.create { body(it) }
fun <T : Any> T.toSingle(): Single<T> = Single.just(this)
fun <T : Any> singleOf(value: T): Single<T> = Single.just(value)
fun <T : Any> Future<T>.toSingle(): Single<T> = Single.fromFuture(this)
fun <T : Any> Callable<T>.toSingle(): Single<T> = Single.fromCallable { this.call() }
fun <T : Any> Throwable.toSingle(): Single<T> = Single.error(this)

