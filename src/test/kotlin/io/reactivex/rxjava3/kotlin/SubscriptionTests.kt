package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.junit.Test
import java.util.concurrent.TimeUnit

class SubscriptionTest {
    @Test fun testSubscriptionAddTo() {
        val compositeSubscription = CompositeDisposable()

        // Create an asynchronous subscription
        // The delay ensures that we don't automatically unsubscribe because data finished emitting
        val subscription = Observable.just("test")
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe()

        assert(!subscription.isDisposed)

        subscription.addTo(compositeSubscription)

        assert(compositeSubscription.size() > 0)
        assert(!subscription.isDisposed)

        compositeSubscription.dispose()

        assert(compositeSubscription.isDisposed)
    }
}