package rx.lang.kotlin

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * subscription += observable.subscribe{}
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