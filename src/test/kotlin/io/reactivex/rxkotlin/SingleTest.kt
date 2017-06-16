package io.reactivex.rxkotlin

import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.concurrent.Callable

class SingleTest : KotlinTests() {
    @Test fun testCreate() {
        Single.create<String> { s ->
            s.onSuccess("Hello World!")
        }.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @Test fun testCreateFromFuture() {
        val future = Observable.just("Hello World!").toFuture()
        val single = future.toSingle()
        single.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @Test fun testCreateFromCallable() {
        val callable = mock(Callable::class.java)

        Mockito.`when`(callable.call()).thenReturn("value")

        callable.toSingle().subscribe { result ->
            a.received(result)
        }

        verify(a, Mockito.times(1)).received("value")
    }

    @Test fun testCreateFromJust() {
        "Hello World!".toSingle()
                .subscribe { result ->
                    a.received(result)
                }
        Mockito.verify(a, Mockito.times(1))
                .received("Hello World!")
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
}