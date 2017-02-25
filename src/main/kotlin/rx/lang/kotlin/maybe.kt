package rx.lang.kotlin

import io.reactivex.Maybe
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T : Any> T?.toMaybe(): Maybe<T> = Maybe.create { s -> if (this != null) s.onSuccess(this); s.onComplete() }
fun <T : Any> Future<T>.toMaybe(): Maybe<T> = Maybe.fromFuture(this)
fun <T : Any> Callable<T>.toMaybe(): Maybe<T> = Maybe.fromCallable(this)
fun <T : Any> (() -> T).toMaybe(): Maybe<T> = Maybe.fromCallable(this)

inline fun <reified R : Any> Maybe<*>.cast(): Maybe<R> = cast(R::class.java)
inline fun <reified R : Any> Maybe<*>.ofType(): Maybe<R> = ofType(R::class.java)