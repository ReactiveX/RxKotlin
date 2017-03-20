package io.reactivex.rxkotlin

import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class SubscribersFunctionsCallsOrderTests {

    private val onStartToken = "onStart"
    private val onNextToken = "onNext"
    private val onSuccessToken = "onSuccessToken"
    private val onCompleteToken = "onComplete"
    private val onFinishToken = "onFinish"
    private val onErrorToken = "onError"

    @Test fun testObservableSubscribeByOnStartOnFinishOrder() {
        val singleEventDelayedObservable = Observable
                .just("test")
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleEventDelayedObservable)
        Assert.assertEquals(listOf(onStartToken, onNextToken, onCompleteToken, onFinishToken), tokens)
    }

    @Test fun testObservableSubscribeByOnStartOnFinishWithError() {
        val singleErrorDelayedObservable = Observable
                .error<String>(TestError())
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleErrorDelayedObservable)
        Assert.assertEquals(listOf(onStartToken, onErrorToken, onFinishToken), tokens)
    }

    @Test fun testFlowableSubscribeByOnStartOnFinishOrder() {
        val singleEventDelayedFlowable = Flowable
                .just("test")
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleEventDelayedFlowable)
        Assert.assertEquals(listOf(onStartToken, onNextToken, onCompleteToken, onFinishToken), tokens)
    }

    @Test fun testFlowableSubscribeByOnStartOnFinishWithError() {
        val singleErrorDelayedFlowable = Observable
                .error<String>(TestError())
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleErrorDelayedFlowable)
        Assert.assertEquals(listOf(onStartToken, onErrorToken, onFinishToken), tokens)
    }

    @Test fun testSingleSubscribeByOnStartOnFinishOrder() {
        val singleEventDelayedFlowable = Single
                .just("test")
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleEventDelayedFlowable)
        Assert.assertEquals(listOf(onStartToken, onSuccessToken, onFinishToken), tokens)
    }

    @Test fun testSingleSubscribeByOnStartOnFinishWithError() {
        val singleErrorDelayedFlowable = Single
                .error<String>(TestError())
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleErrorDelayedFlowable)
        Assert.assertEquals(listOf(onStartToken, onErrorToken, onFinishToken), tokens)
    }

    @Test fun testMaybeSubscribeByOnStartOnFinishOrder() {
val singleEventDelayedFlowable = Maybe
        .just("test")
        .delay(100, TimeUnit.MILLISECONDS)
val tokens = getTokenOrderFor(singleEventDelayedFlowable)
        Assert.assertEquals(listOf(onSuccessToken), tokens)
    }

    @Test fun testMaybeSubscribeByOnStartOnFinishWithError() {
        val singleErrorDelayedFlowable = Maybe
                .error<String>(TestError())
                .delay(100, TimeUnit.MILLISECONDS)
        val tokens = getTokenOrderFor(singleErrorDelayedFlowable)
        Assert.assertEquals(listOf(onErrorToken), tokens)
    }

    @Test fun testCompletableSubscribeByOnStartOnFinishOrder() {
        val singleEventDelayedObservable = Observable.just("test").delay(100, TimeUnit.MILLISECONDS)
        val singleDelayedCompletable = Completable.fromObservable(singleEventDelayedObservable)
        val tokens = getTokenOrderFor(singleDelayedCompletable)
        Assert.assertEquals(listOf(onCompleteToken), tokens)
    }

    @Test fun testCompletableSubscribeByOnStartOnFinishWithError() {
        val singleErrorDelayedObservable = Observable.error<String>(TestError()).delay(100, TimeUnit.MILLISECONDS)
        val singleErrorDelayedCompletable = Completable.fromObservable(singleErrorDelayedObservable)
        val tokens = getTokenOrderFor(singleErrorDelayedCompletable)
        Assert.assertEquals(listOf(onErrorToken), tokens)
    }

    private fun <T : Any> getTokenOrderFor(observable: Observable<T>): List<String> {
        val latch = CountDownLatch(1)
        var tokens = listOf<String>()
        val subscription = observable.subscribeBy(
                onStart = { tokens += onStartToken },
                onNext = { tokens += onNextToken },
                onComplete = { tokens += onCompleteToken },
                onError = { tokens += onErrorToken },
                onFinish = { tokens += onFinishToken; latch.countDown() }
        )
        latch.await(5, TimeUnit.SECONDS)
        subscription.dispose()
        return tokens
    }

    private fun <T : Any> getTokenOrderFor(flowable: Flowable<T>): List<String> {
        val latch = CountDownLatch(1)
        var tokens = listOf<String>()
        val subscription = flowable.subscribeBy(
                onStart = { tokens += onStartToken },
                onNext = { tokens += onNextToken },
                onComplete = { tokens += onCompleteToken },
                onError = { tokens += onErrorToken },
                onFinish = { tokens += onFinishToken; latch.countDown() }
        )
        latch.await(5, TimeUnit.SECONDS)
        subscription.dispose()
        return tokens
    }

    private fun <T : Any> getTokenOrderFor(single: Single<T>): List<String> {
        val latch = CountDownLatch(1)
        var tokens = listOf<String>()
        val subscription = single.subscribeBy(
                onStart = { tokens += onStartToken },
                onSuccess = { tokens += onSuccessToken },
                onError = { tokens += onErrorToken },
                onFinish = { tokens += onFinishToken; latch.countDown() }
        )
        latch.await(5, TimeUnit.SECONDS)
        subscription.dispose()
        return tokens
    }

    private fun <T : Any> getTokenOrderFor(maybe: Maybe<T>): List<String> {
        val latch = CountDownLatch(1)
        var tokens = listOf<String>()
        val subscription = maybe.subscribeBy(
                onSuccess = { tokens += onSuccessToken },
                onComplete = { tokens += onCompleteToken; latch.countDown() },
                onError = { tokens += onErrorToken; latch.countDown() }
        )
        latch.await(5, TimeUnit.SECONDS)
        subscription.dispose()
        return tokens
    }

    private fun getTokenOrderFor(completable: Completable): List<String> {
        val latch = CountDownLatch(1)
        var tokens = listOf<String>()
        val subscription = completable.subscribeBy(
                onComplete = { tokens += onCompleteToken; latch.countDown() },
                onError = { tokens += onErrorToken; latch.countDown() }
        )
        latch.await(5, TimeUnit.SECONDS)
        subscription.dispose()
        return tokens
    }

    class TestError : Throwable()
}