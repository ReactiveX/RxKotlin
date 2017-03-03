package rx.lang.kotlin

import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import rx.Observable
import java.util.concurrent.Callable
import org.junit.Test as test

class SingleTest : KotlinTests() {
    @test fun testCreate() {
        single<String> { s ->
            s.onSuccess("Hello World!")
        }.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @test fun testCreateFromFuture() {
        val future = Observable.just("Hello World!").toBlocking().toFuture()
        val single = future.toSingle()
        single.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @test fun testCreateFromCallable() {
        val callable = mock(Callable::class.java)

        Mockito.`when`(callable.call()).thenReturn("value")

        callable.toSingle().subscribe { result ->
            a.received(result)
        }

        verify(a, Mockito.times(1)).received("value")
    }

    @test fun testCreateFromJust() {
        "Hello World!".toSingle().subscribe { result ->
            a.received(result)
        }
        Mockito.verify(a, Mockito.times(1)).received("Hello World!")
    }
}