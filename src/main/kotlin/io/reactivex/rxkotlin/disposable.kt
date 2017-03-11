package io.reactivex.rxkotlin

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * disposable += observable.subscribe()
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

/**
 * Add the subscription to a CompositeSubscription.
 * @param compositeDisposable CompositeDisposable to add this subscription to
 * @return this instance
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable
        = apply { compositeDisposable.add(this) }