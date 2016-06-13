package rx.lang.kotlin

import org.junit.Test
import rx.Observable
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class SubscriptionTest {
    @Test fun testSubscriptionAddTo() {
        val compositeSubscription = CompositeSubscription()

        // Create an asynchronous subscription
        // The delay ensures that we don't automatically unsubscribe because data finished emitting
        val subscription = Observable.just("test")
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribe();

        assert(!subscription.isUnsubscribed)

        subscription.addTo(compositeSubscription);

        assert(compositeSubscription.hasSubscriptions());
        assert(!subscription.isUnsubscribed);

        compositeSubscription.unsubscribe()

        assert(compositeSubscription.isUnsubscribed);
    }
}