package rx.lang.kotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import rx.Subscriber
import rx.exceptions.OnErrorNotImplementedException
import java.util.*
import org.junit.Test as test

class SubscribersTest { /*
    @test fun testEmptySubscriber() {
        val s = subscriber<Int>()
        callSubscriberMethods(false, s)
    }

    @test fun testSubscriberConstruction() {
        val events = ArrayList<String>()

        callSubscriberMethods(false, subscriber<Int>().
                onNext {events.add("onNext($it)")}
        )

        assertEquals(listOf("onNext(1)"), events)
        events.clear()

        callSubscriberMethods(true, subscriber<Int>().
                onNext {events.add("onNext($it)")}.
                onError { events.add(it.javaClass.simpleName) }
        )

        assertEquals(listOf("onNext(1)", "RuntimeException"), events)
        events.clear()

        callSubscriberMethods(true, subscriber<Int>().
                onNext {events.add("onNext($it)")}.
                onError { events.add(it.javaClass.simpleName) }.
                onCompleted { events.add("onCompleted") }
        )

        assertEquals(listOf("onNext(1)", "RuntimeException", "onCompleted"), events)
        events.clear()
    }

    @test(expected = OnErrorNotImplementedException::class)
    fun testNoErrorHandlers() {
        subscriber<Int>().onError(Exception("expected"))
    }

    @test fun testErrorHandlers() {
        var errorsCaught = 0

        subscriber<Int>().
                onError { errorsCaught++ }.
                onError { errorsCaught++ }.
                onError(Exception("expected"))

        assertEquals(2, errorsCaught)
    }

    @test fun testMultipleOnNextHandlers() {
        var nextCaught = 0

        subscriber<Int>().
                onNext { nextCaught ++ }.
                onNext { nextCaught ++ }.
                onNext(1)

        assertEquals(2, nextCaught)
    }

    @test fun testOnStart() {
        var started = false
        subscriber<Int>().onStart { started = true }.onStart()
        assertTrue(started)
    }

    private fun callSubscriberMethods(hasOnError : Boolean, s: Subscriber<Int>) {
        s.onStart()
        s.onNext(1)
        try {
            s.onError(RuntimeException())
        } catch (t : Throwable) {
            if (hasOnError) {
                throw t
            }
        }
        s.onCompleted()
    }

    @test fun testSubscribeWith() {
        val completeObservable = observable<Int> {
            it.onNext(1)
            it.onCompleted()
        }

        val events = ArrayList<String>()

        completeObservable.subscribeWith {
            onNext { events.add("onNext($it)") }
        }

        assertEquals(listOf("onNext(1)"), events)
        events.clear()

        completeObservable.subscribeWith {
            onNext { events.add("onNext($it)") }
            onCompleted { events.add("onCompleted") }
        }

        assertEquals(listOf("onNext(1)", "onCompleted"), events)
        events.clear()

        val errorObservable = observable<Int> {
            it.onNext(1)
            it.onError(RuntimeException())
        }

        errorObservable.subscribeWith {
            onNext { events.add("onNext($it)") }
            onError { events.add("onError(${it.javaClass.simpleName})") }
        }

        assertEquals(listOf("onNext(1)", "onError(RuntimeException)"), events)
        events.clear()

        try {
            errorObservable.subscribeWith {
                onNext { events.add("onNext($it)") }
            }
        } catch (t: Throwable) {
            events.add("catch(${t.javaClass.simpleName})")
        }

        assertEquals(listOf("onNext(1)", "catch(OnErrorNotImplementedException)"), events)
        events.clear()
    }

    @test fun testSingleSubscribeWith() {
        val events = ArrayList<String>()
        val successSingle = singleOf(1)

        successSingle.subscribeWith {
            onSuccess { events.add("onSuccess($it)") }
        }

        assertEquals(listOf("onSuccess(1)"), events)
        events.clear()

        val errorSingle = RuntimeException().toSingle<Int>()

        errorSingle.subscribeWith {
            onSuccess { events.add("onSuccess($it)") }
            onError { events.add("onError(${it.javaClass.simpleName})") }
        }

        assertEquals(listOf("onError(RuntimeException)"), events)
        events.clear()

        try {
            errorSingle.subscribeWith {
                onSuccess { events.add("onSuccess($it)") }
            }
        } catch (e: Throwable) {
            events.add("catch(${e.javaClass.simpleName})")
        }

        assertEquals(listOf("catch(OnErrorNotImplementedException)"), events)
        events.clear()
    }

    @test fun testIdiomaticAdd() {
        var subscriptionCalled = false
        val s = subscriber<Int>()

        s.add { subscriptionCalled = true }
        s.unsubscribe()

        assertTrue(subscriptionCalled)
    } */
}
