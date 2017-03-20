package io.reactivex.rxkotlin

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) }
private val onCompleteStub: () -> Unit = {}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Observable<T>.subscribeBy(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null
): Disposable {
    onStart?.invoke()
    val withOnFinish = if(onFinish != null) doFinally { onFinish() } else this
    return withOnFinish.subscribe(onNext, onError, onComplete)
}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Flowable<T>.subscribeBy(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null
): Disposable {
    onStart?.invoke()
    val withOnFinish = if(onFinish != null) doFinally { onFinish() } else this
    return withOnFinish.subscribe(onNext, onError, onComplete)
}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Single<T>.subscribeBy(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null
): Disposable {
    onStart?.invoke()
    val withOnFinish = if(onFinish != null) doFinally { onFinish() } else this
    return withOnFinish.subscribe(onSuccess, onError)
}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Maybe<T>.subscribeBy(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null
): Disposable {
    onStart?.invoke()
    val withOnFinish = if(onFinish != null) doFinally { onFinish() } else this
    return withOnFinish.subscribe(onSuccess, onError, onComplete)
}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun Completable.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null
): Disposable {
    onStart?.invoke()
    val withOnFinish = if(onFinish != null) doFinally { onFinish() } else this
    return withOnFinish.subscribe(onComplete, onError)
}

class OnErrorNotImplementedException(e: Throwable) : RuntimeException(e)
