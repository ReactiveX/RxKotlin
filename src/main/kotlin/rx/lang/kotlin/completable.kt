package rx.lang.kotlin

import rx.Completable
import rx.Single
import rx.functions.Action0
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun completable(body : (s : Completable.CompletableSubscriber) -> Unit): Completable = Completable.create(body)
fun Action0.toCompletable(): Completable = Completable.fromAction(this)
fun <T> completableOf(f: Function0<T>): Completable = Completable.fromAction { f.invoke() }
fun <T> Callable<T>.toCompletable(): Completable = Completable.fromCallable { this.call() }
fun <T> Future<T>.toCompletable(): Completable = Completable.fromFuture(this)
fun <T> Single<T>.toCompletable(): Completable = Completable.fromSingle(this)