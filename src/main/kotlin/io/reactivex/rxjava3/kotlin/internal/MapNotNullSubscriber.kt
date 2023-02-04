package io.reactivex.rxjava3.kotlin.internal

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableConditionalSubscriber
import io.reactivex.rxjava3.internal.subscribers.BasicFuseableSubscriber
import org.reactivestreams.Subscriber

internal class FlowableMapNotNullFlowable<T : Any, R : Any>(
        private val source: Flowable<T>,
        private val transform: (T) -> R?,
) : Flowable<R>() {
    override fun subscribeActual(subscriber: Subscriber<in R>) {
        if (subscriber is ConditionalSubscriber<*>) {
            source.subscribe(
                    MapNotNullConditionalSubscriber<T, R>(
                            subscriber as ConditionalSubscriber<in R>,
                            transform
                    )
            )
        } else {
            source.subscribe(MapNotNullSubscriber(subscriber, transform))
        }
    }

    internal class MapNotNullSubscriber<T : Any, R : Any>(
            downstream: Subscriber<in R>,
            val transform: (T) -> R?
    ) : BasicFuseableSubscriber<T, R>(downstream),
            ConditionalSubscriber<T> {
        override fun onNext(t: T) {
            if (!tryOnNext(t)) {
                upstream.request(1)
            }
        }

        override fun tryOnNext(t: T): Boolean {
            if (done) {
                return true
            }
            if (sourceMode != NONE) {
                downstream.onNext(null)
                return true
            }

            val result = try {
                transform(t)
            } catch (ex: Throwable) {
                fail(ex)
                return true
            }

            if (result !== null) {
                downstream.onNext(result)
                return true
            }
            return false
        }

        override fun requestFusion(mode: Int): Int = transitiveBoundaryFusion(mode)

        @Throws(Throwable::class)
        override fun poll(): R? {
            while (true) {
                val item = qs.poll() ?: return null
                val result = transform(item)
                if (result != null) {
                    return result
                }

                if (sourceMode == ASYNC) {
                    qs.request(1)
                }
            }
        }
    }

    internal class MapNotNullConditionalSubscriber<T : Any, R : Any>(
            downstream: ConditionalSubscriber<in R>,
            val transform: (T) -> R?,
    ) : BasicFuseableConditionalSubscriber<T, R>(downstream) {
        override fun onNext(t: T) {
            if (!tryOnNext(t)) {
                upstream.request(1)
            }
        }

        override fun tryOnNext(t: T): Boolean {
            if (done) {
                return true
            }
            if (sourceMode != NONE) {
                downstream.onNext(null)
                return true
            }

            val result = try {
                transform(t)
            } catch (ex: Throwable) {
                fail(ex)
                return true
            }

            return if (result != null) {
                downstream.tryOnNext(result)
            } else {
                false
            }
        }

        override fun requestFusion(mode: Int): Int {
            return transitiveBoundaryFusion(mode)
        }

        @Throws(Throwable::class)
        override fun poll(): R? {
            while (true) {
                val item = qs.poll() ?: return null
                val result = transform(item)
                if (result != null) {
                    return result
                }
                if (sourceMode == ASYNC) {
                    qs.request(1)
                }
            }
        }
    }
}