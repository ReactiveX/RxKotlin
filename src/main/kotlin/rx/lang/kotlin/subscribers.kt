package rx.lang.kotlin

import rx.Completable
import rx.Observable
import rx.Single
import rx.Subscription
import rx.exceptions.OnErrorNotImplementedException
import rx.observables.BlockingObservable

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) }
private val onCompleteStub: () -> Unit = {}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Observable<T>.subscribeBy(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onCompleted: () -> Unit = onCompleteStub
): Subscription = subscribe(onNext, onError, onCompleted)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> BlockingObservable<T>.subscribeBy(
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onCompleted: () -> Unit = onCompleteStub
) = subscribe(onNext, onError, onCompleted)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Single<T>.subscribeBy(
        onSuccess: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub
): Subscription = subscribe(onSuccess, onError)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun Completable.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onCompleted: () -> Unit = onCompleteStub
): Subscription = subscribe(onCompleted, onError)
