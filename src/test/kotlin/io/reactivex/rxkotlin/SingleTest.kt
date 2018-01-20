package io.reactivex.rxkotlin

import io.reactivex.Single
import io.reactivex.observers.LambdaConsumerIntrospection
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class SingleTest : KotlinTests() {
    @Test fun testCreate() {
        Single.create<String> { s ->
            s.onSuccess("Hello World!")
        }.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @Test
    fun testSubscribeBy() {
        Single.just("Alpha")
                .subscribeBy {
                    a.received(it)
                }
        verify(a, Mockito.times(1))
                .received("Alpha")
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
                .map { Single.just(it) }
                .concatAll()
                .toList()
                .subscribe { result ->
                    Assert.assertEquals((0 until 10).toList(), result)
                }
    }
}
