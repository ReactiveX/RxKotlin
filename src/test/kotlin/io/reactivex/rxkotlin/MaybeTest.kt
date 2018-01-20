package io.reactivex.rxkotlin

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.observers.LambdaConsumerIntrospection
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.atomic.AtomicReference

class MaybeTest {
    @Test
    fun testSubscribeBy() {
        val first = AtomicReference<String>()

        Maybe.just("Alpha")
                .subscribeBy {
                    first.set(it)
                }
        Assert.assertTrue(first.get() == "Alpha")
    }

    @Test
    fun testSubscribeByErrorIntrospection() {
        val disposable = Single.just(Unit)
                .subscribeBy() as LambdaConsumerIntrospection
        Assert.assertFalse(disposable.hasCustomOnError())
    }

    @Test
    fun testSubscribeByErrorIntrospectionCustom() {
        val disposable = Single.just(Unit)
                .subscribeBy(onError = {}) as LambdaConsumerIntrospection
        Assert.assertTrue(disposable.hasCustomOnError())
    }

    @Test fun testConcatAll() {
        (0 until 10)
                .map { Maybe.just(it) }
                .concatAll()
                .toList()
                .subscribe { result ->
                    Assert.assertEquals((0 until 10).toList(), result)
                }
    }
}
