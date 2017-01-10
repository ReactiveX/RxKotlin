package rx.lang.kotlin

import org.mockito.Mockito
import java.util.concurrent.Callable
import org.junit.Test as test
import org.mockito.Mockito.verify
import org.mockito.Mockito.mock

class SingleTest : KotlinTests() {
    @test fun testCreate() {
        single<String> { s ->
            s.onSuccess("Hello World!");
        }.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @test fun testCreateFromFuture() {
        val future = "Hello World!".toSingletonObservable().toBlocking().toFuture()
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
        singleOf("Hello World!").subscribe { result ->
            a.received(result)
        }
        Mockito.verify(a, Mockito.times(1)).received("Hello World!")
    }

    @test fun testCreateFromGeneric() {
        "Hello World!".toSingle().subscribe { result ->
            a.received(result)
        }
        Mockito.verify(a, Mockito.times(1)).received("Hello World!")

        1337.toSingle().subscribe { result ->
            a.received(result)
        }
        Mockito.verify(a, Mockito.times(1)).received(1337)
    }
}