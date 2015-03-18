package rx.lang.kotlin

import org.junit.Test as test
import rx.Subscriber
import java.util.ArrayList
import kotlin.test.assertEquals

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

    private fun callSubscriberMethods(hasOnError : Boolean, s: Subscriber<Int>) {
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