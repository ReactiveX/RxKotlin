package io.reactivex.rxkotlin

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { throw it }
private val onCompleteStub: () -> Unit = {}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Observable<T>.subscribeBy(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onNext, onError, onComplete)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Flowable<T>.subscribeBy(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onNext, onError, onComplete)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Single<T>.subscribeBy(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub
): Disposable = subscribe(onSuccess, onError)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Maybe<T>.subscribeBy(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onSuccess, onError, onComplete)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun Completable.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onComplete, onError)
