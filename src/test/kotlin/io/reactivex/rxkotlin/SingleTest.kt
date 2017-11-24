package io.reactivex.rxkotlin

import io.reactivex.Single
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