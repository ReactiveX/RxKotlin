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
 * replacement for CompositeDisposable.addAll -> disposable += arrayOf(observable.subscribe())
 */
operator fun CompositeDisposable.plusAssign(disposables: Array<Disposable>) {
    disposables.forEach { disposable ->
        add(disposable)
    }
}

/**
 * Add the disposable to a CompositeDisposable.
 * @param compositeDisposable CompositeDisposable to add this disposable to
 * @return this instance
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable
        = apply { compositeDisposable.add(this) }
