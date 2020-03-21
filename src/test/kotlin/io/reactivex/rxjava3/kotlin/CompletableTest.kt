package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.observers.LambdaConsumerIntrospection
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import java.util.*
import java.util.concurrent.Callable

class CompletableTest : KotlinTests() {

    @Test fun testCreateFromAction() {
        var count = 0
        val c1 = Action { count++ }.toCompletable()
        assertNotNull(c1)
        c1.subscribe()
        assertEquals(1, count)
    }

    @Test fun testCreateFromCallable() {
        var count = 0
        val c1 = Callable { count++ }.toCompletable()
        assertNotNull(c1)
        c1.subscribe()
        assertEquals(1, count)
    }

    @Test fun createFromLambda() {
        var count = 0
        val c2 = { count++ }.toCompletable()
        assertNotNull(c2)
        c2.subscribe()
        assertEquals(1, count)
    }

    @Test(expected = NoSuchElementException::class) fun testCreateFromSingle() {
        val c1 = Single.just("Hello World!").ignoreElement()
        assertNotNull(c1)
        c1.toObservable<String>().blockingFirst()
    }

    @Test fun testConcatAll() {
        val list = emptyList<Int>().toMutableList()
        (0 until 10)
                .map { v -> Completable.create { list += v } }
                .concatAll()
                .subscribe {
                    Assert.assertEquals((0 until 10).toList(), list)
                }
    }

    @Test
    fun testSubscribeBy() {
        Completable.complete()
                .subscribeBy {
                    a.received(Unit)
                }
        Mockito.verify(a, Mockito.times(1))
                .received(Unit)
    }

    @Test
    fun testSubscribeByErrorIntrospection() {
        val disposable = Completable.complete()
                .subscribeBy() as LambdaConsumerIntrospection
        Assert.assertFalse(disposable.hasCustomOnError())
    }

    @Test
    fun testSubscribeByErrorIntrospectionCustom() {
        val disposable = Completable.complete()
                .subscribeBy(onError = {}) as LambdaConsumerIntrospection
        Assert.assertTrue(disposable.hasCustomOnError())
    }

    @Test
    fun testBlockingSubscribeBy() {
        Completable.complete()
                .blockingSubscribeBy {
                    a.received(Unit)
                }
        Mockito.verify(a, Mockito.times(1))
                .received(Unit)
    }
}
