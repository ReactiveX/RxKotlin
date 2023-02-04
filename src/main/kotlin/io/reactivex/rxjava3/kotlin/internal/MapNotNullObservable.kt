package io.reactivex.rxjava3.kotlin.internal

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.internal.observers.BasicFuseableObserver

internal class MapNotNullObservable<T : Any, R : Any>(
        private val source: Observable<T>,
        private val transform: (T) -> R?
) : Observable<R>() {
    override fun subscribeActual(observer: Observer<in R>) {
        source.subscribe(MapOptionalObserver(observer, transform))
    }

    internal class MapOptionalObserver<T : Any, R : Any>(
            downstream: Observer<in R>,
            internal val transform: (T) -> R?
    ) : BasicFuseableObserver<T, R>(downstream) {
        override fun onNext(t: T) {
            if (done) {
                return
            }
            if (sourceMode != NONE) {
                downstream.onNext(null)
                return
            }
            val result = try {
                transform(t)
            } catch (ex: Throwable) {
                fail(ex)
                return
            }
            if (result !== null) {
                downstream.onNext(result)
            }
        }

        override fun requestFusion(mode: Int): Int {
            return transitiveBoundaryFusion(mode)
        }

        @Throws(Throwable::class)
        override fun poll(): R? {
            while (true) {
                val item = qd.poll() ?: return null
                val result = transform(item)
                if (result !== null) {
                    return result
                }
            }
        }
    }
}