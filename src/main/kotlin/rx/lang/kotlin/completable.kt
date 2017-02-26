package rx.lang.kotlin

import rx.Completable
import rx.functions.Action0
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun Action0.toCompletable(): Completable = Completable.fromAction(this)
fun <T> Callable<T>.toCompletable(): Completable = Completable.fromCallable { this.call() }
fun <T> Future<T>.toCompletable(): Completable = Completable.fromFuture(this)
fun (() -> Any).toCompletable(): Completable = Completable.fromCallable(this)