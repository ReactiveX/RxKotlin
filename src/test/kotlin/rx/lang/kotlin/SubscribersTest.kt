package rx.lang.kotlin

import org.junit.Test as test
import rx.Subscriber
import rx.exceptions.OnErrorNotImplementedException
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

    test fun testSubscribeWith() {
        val completeObservable = observable<Int> {
            it.onNext(1)
            it.onCompleted()
        }

        val events = ArrayList<String>()

        val s1 = completeObservable.subscribeWith {
            onNext { events.add("onNext($it)") }
        }

        assertEquals(listOf("onNext(1)"), events)
        events.clear()
        s1.unsubscribe()

        val s2 = completeObservable.subscribeWith {
            onNext { events.add("onNext($it)") }
            onCompleted { events.add("onCompleted") }
        }

        assertEquals(listOf("onNext(1)", "onCompleted"), events)
        events.clear()
        s2.unsubscribe()

        val errorObservable = observable<Int> {
            it.onNext(1)
            it.onError(RuntimeException())
        }

        val s3 = errorObservable.subscribeWith {
            onNext { events.add("onNext($it)") }
            onError { events.add("onError(${it.javaClass.getSimpleName()})") }
        }

        assertEquals(listOf("onNext(1)", "onError(RuntimeException)"), events)
        events.clear()
        s3.unsubscribe()

        try {
            val s4 = errorObservable.subscribeWith {
                onNext { events.add("onNext($it)") }
            }
        } catch (t: Throwable) {
            events.add("catch(${t.javaClass.getSimpleName()})")
        }

        assertEquals(listOf("onNext(1)", "catch(OnErrorNotImplementedException)"), events)
        events.clear()
        s1.unsubscribe()

    }
}