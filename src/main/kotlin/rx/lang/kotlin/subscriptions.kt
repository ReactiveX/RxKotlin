package rx.lang.kotlin

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * subscription += observable.subscribe()
 */
operator fun CompositeDisposable.plusAssign(subscription: Disposable) {
    add(subscription)
}

/**
 * Add the subscription to a CompositeSubscription.
 * @param compositeSubscription CompositeSubscription to add this subscription to
 * @return this instance
 */
fun Disposable.addTo(compositeSubscription: CompositeDisposable): Disposable
        = apply { compositeSubscription.add(this) }

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
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
