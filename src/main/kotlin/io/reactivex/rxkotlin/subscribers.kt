package io.reactivex.rxkotlin

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { RxJavaPlugins.onError(OnErrorNotImplementedException(it)) }
private val onCompleteStub: () -> Unit = {}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> Observable<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
        ): Disposable = subscribe(onNext, onError, onComplete)

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> Flowable<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
        ): Disposable = subscribe(onNext, onError, onComplete)

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> Single<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onSuccess: (T) -> Unit = onNextStub
        ): Disposable = subscribe(onSuccess, onError)

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun <T : Any> Maybe<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onSuccess: (T) -> Unit = onNextStub
        ): Disposable = subscribe(onSuccess, onError, onComplete)

/**
 * Overloaded subscribe function that allows passing named parameters
 */
fun Completable.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onComplete, onError)

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
fun <T : Any> Observable<T>.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
        ) = blockingSubscribe(onNext, onError, onComplete)

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
fun <T : Any> Flowable<T>.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
        ) = blockingSubscribe(onNext, onError, onComplete)
