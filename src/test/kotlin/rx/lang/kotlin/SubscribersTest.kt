package rx.lang.kotlin

import rx.Subscriber
import rx.exceptions.OnErrorNotImplementedException
import java.util.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.Test as test

public class SubscribersTest {
    test fun testEmptySubscriber() {
        val s = subscriber<Int>()
        callSubscriberMethods(false, s)
    }

    test fun testSubscriberConstruction() {
        val events = ArrayList<String>()

        callSubscriberMethods(false, subscriber<Int>().
                onNext {events.add("onNext($it)")}
        )

        assertEquals(listOf("onNext(1)"), events)
        events.clear()

        callSubscriberMethods(true, subscriber<Int>().
                onNext {events.add("onNext($it)")}.
                onError { events.add(it.javaClass.getSimpleName()) }
        )

        assertEquals(listOf("onNext(1)", "RuntimeException"), events)
        events.clear()

        callSubscriberMethods(true, subscriber<Int>().
                onNext {events.add("onNext($it)")}.
                onError { events.add(it.javaClass.getSimpleName()) }.
                onCompleted { events.add("onCompleted") }
        )

        assertEquals(listOf("onNext(1)", "RuntimeException", "onCompleted"), events)
        events.clear()
    }

    test(expected = javaClass<OnErrorNotImplementedException>())
    fun testNoErrorHandlers() {
        subscriber<Int>().onError(Exception("expected"))
    }

    test fun testErrorHandlers() {
        var errorsCaught = 0

        subscriber<Int>().
                onError { errorsCaught++ }.
                onError { errorsCaught++ }.
                onError(Exception("expected"))

        assertEquals(2, errorsCaught)
    }

    test fun testMultipleOnNextHandlers() {
        var nextCaught = 0

        subscriber<Int>().
                onNext { nextCaught ++ }.
                onNext { nextCaught ++ }.
                onNext(1)

        assertEquals(2, nextCaught)
    }

    test fun testOnStart() {
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
}