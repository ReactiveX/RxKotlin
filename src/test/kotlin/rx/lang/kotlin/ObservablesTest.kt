package rx.lang.kotlin

import kotlin.test.assertEquals
import org.junit.Test as test
import rx.Observable
import kotlin.test.fail
import kotlin.test.assertNotNull
import java.util.concurrent.atomic.AtomicInteger
import org.junit.Ignore


public class ObservablesTest {
    test fun testCreation() {
        val o0 : Observable<Int> = emptyObservable()
        observable<Int> { s -> s.onNext(1); s.onNext(777); s.onCompleted() }.toList().forEach {
            assertEquals(listOf(1, 777), it)
        }
        val o1 : Observable<Int> = listOf(1, 2, 3).toObservable()
        val o2 : Observable<List<Int>> = listOf(1, 2, 3).toSingletonObservable()

        val o3 : Observable<Int> = deferredObservable { observable<Int> { s -> s.onNext(1) } }
        val o4 : Observable<Int> = Array(3) {0}.toObservable()
        val o5 : Observable<Int> = IntArray(3).toObservable()

        assertNotNull(o0)
        assertNotNull(o1)
        assertNotNull(o2)
        assertNotNull(o3)
        assertNotNull(o4)
        assertNotNull(o5)
    }

    test fun testExampleFromReadme() {
        val result = observable<String> { subscriber ->
            subscriber.onNext("H")
            subscriber.onNext("e")
            subscriber.onNext("l")
            subscriber.onNext("")
            subscriber.onNext("l")
            subscriber.onNext("o")
            subscriber.onCompleted()
        }.filter { it.isNotEmpty() }.
        fold (StringBuilder()) { sb, e -> sb.append(e) }.
        map { it.toString() }.
        toBlocking().single()

        assertEquals("Hello", result)
    }

    test fun iteratorObservable() {
        assertEquals(listOf(1,2,3), listOf(1,2,3).iterator().toObservable().toList().toBlocking().single())
    }

    test fun intProgressionStep1Empty() {
        assertEquals(listOf(1), (1..1).toObservable().toList().toBlocking().first())
    }
    test fun intProgressionStep1() {
        assertEquals((1..10).toList(), (1..10).toObservable().toList().toBlocking().first())
    }

    test fun intProgressionDownto() {
        assertEquals((1 downTo 10).toList(), (1 downTo 10).toObservable().toList().toBlocking().first())
    }

    Ignore
    test fun intProgressionOverflow() {
        // too slow
        assertEquals((0..10).toList().reverse(), (-10 .. Integer.MAX_VALUE).toObservable().skip(Integer.MAX_VALUE).map{Integer.MAX_VALUE - it}.toList().toBlocking().first())
    }

    test fun filterNotNull() {
        val o : Observable<Int> = listOf(1, null).toObservable().filterNotNull()
        o.toList().forEach {
            assertEquals(listOf(1), it)
        }
    }

    test fun requireNoNullsWithoutNulls() {
        (listOf(1,2) as List<Int?>).toObservable().requireNoNulls().subscribe()
    }

    test fun requireNoNulls() {
        try {
            val o : Observable<Int> = listOf(1, null).toObservable().requireNoNulls()

            o.subscribe()
            fail("shouldn't reach here")
        } catch (expected : Throwable) {
        }
    }

    test fun testWithIndex() {
        listOf("a", "b", "c").toObservable().
                withIndex().
                toList().
                forEach {
                    assertEquals(listOf(IndexedValue(0, "a"), IndexedValue(1, "b"), IndexedValue(2, "c")), it)
                }
    }

    test fun testFold() {
        listOf(1, 2, 3).toObservable().fold(0) {acc, e -> acc + e}.single().forEach {
            assertEquals(6, it)
        }
    }

    test fun `kotlin sequence should produce expected items and observable be able to handle em`() {
        kotlin.sequence(0) {it + 1}.toObservable().take(3).toList().forEach {
            assertEquals(listOf(0, 1, 2), it)
        }
    }

    test fun `infinite iterable should not hang or produce too many elements`() {
        val generated = AtomicInteger()
        kotlin.sequence { generated.incrementAndGet() }.toObservable().
                take(100).
                toList().
                subscribe()

        assertEquals(101, generated.get())
    }

    test fun testFlatMapSequence() {
        assertEquals(
                listOf(1, 2, 3, 2, 3, 4, 3, 4, 5),
            listOf(1,2,3).toObservable().flatMapSequence { listOf(it, it + 1, it + 2).asSequence() }.toList().toBlocking().single()
        )
    }
}