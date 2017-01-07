package rx.lang.kotlin

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Action
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun Action.toCompletable(): Completable = Completable.fromAction(this)
inline fun <T : Any> completableOf(crossinline f: () -> T): Completable = Completable.fromAction { f() }
fun <T : Any> Callable<T>.toCompletable(): Completable = Completable.fromCallable { this.call() }
fun <T : Any> Future<T>.toCompletable(): Completable = Completable.fromFuture(this)
fun <T : Any> Single<T>.toCompletable(): Completable = Completable.fromSingle(this)