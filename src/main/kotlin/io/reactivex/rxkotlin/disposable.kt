package io.reactivex.rxkotlin

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

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
fun Disposable.addTo(compositeDisposable: DisposableContainer): Disposable
        = apply { compositeDisposable.add(this) }
