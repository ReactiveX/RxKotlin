package io.reactivex.rxkotlin

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * disposable += observable.subscribe()
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

/**
 * Add the disposable to a CompositeDisposable.
 * @param compositeDisposable CompositeDisposable to add this disposable to
 * @return this instance
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable =
        apply { compositeDisposable.add(this) }
