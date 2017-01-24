package rx.lang.kotlin

import io.reactivex.Completable
import io.reactivex.functions.Action
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun Action.toCompletable(): Completable = Completable.fromAction(this)
fun Callable<out Any>.toCompletable(): Completable = Completable.fromCallable(this)
fun Future<out Any>.toCompletable(): Completable = Completable.fromFuture(this)
fun (() -> Any).toCompletable(): Completable = Completable.fromCallable(this)
