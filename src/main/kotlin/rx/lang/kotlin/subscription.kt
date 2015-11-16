package rx.lang.kotlin

import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * subscription += observable.subscribe{}
 */
operator fun CompositeSubscription.plusAssign(subscription: Subscription) = add(subscription)