package io.reactivex.rxjava3.kotlin.internal

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.disposables.DisposableHelper

internal class MapNotNullObserver<T : Any, R : Any>(
        private val downstream: Observer<R>,
        private val transform: (T) -> R?
) : Observer<T>, Disposable {
    private var upstream: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        if (DisposableHelper.validate(upstream, d)) {
            upstream = d
            downstream.onSubscribe(this)
        }
    }

    override fun onNext(t: T) {
        transform(t)?.let(downstream::onNext)
    }

    override fun onError(e: Throwable): Unit = downstream.onError(e)

    override fun onComplete(): Unit = downstream.onComplete()

    override fun dispose() {
        upstream!!.dispose()
    }

    override fun isDisposed(): Boolean = upstream!!.isDisposed
}