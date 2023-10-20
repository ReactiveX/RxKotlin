package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SubscriptionTest {

    // Create an asynchronous subscription
    // The delay ensures that we don't automatically unsubscribe because data finished emitting
    private val subscription = Observable.just("test")
        .delay(100, TimeUnit.MILLISECONDS)
        .subscribe()

    private lateinit var compositeSubscription: CompositeDisposable

    @Before
    fun setUp() {
        compositeSubscription = CompositeDisposable()
    }

    @After
    fun tearDown() {
        compositeSubscription.dispose()
        assert(compositeSubscription.isDisposed)
    }

    @Test
    fun testSubscriptionAddTo() {
        assert(!subscription.isDisposed)

        subscription.addTo(compositeSubscription)

        assert(compositeSubscription.size() > 0)
        assert(!subscription.isDisposed)
    }

    @Test
    fun testSubscriptionAddToWithPlusAssign() {
        compositeSubscription += subscription

        assert(compositeSubscription.size() > 0)
    }

    @Test
    fun testSubscriptionDeleteWithMinusAssign() {
        this.testSubscriptionAddToWithPlusAssign()

        compositeSubscription -= subscription

        assert(compositeSubscription.size() == 0)
    }
}