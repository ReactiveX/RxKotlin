package io.reactivex.rxkotlin

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Flowable.create
import io.reactivex.FlowableEmitter
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Ignore
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

class FlowableTest {

    private fun <T : Any> bufferedFlowable(source: (FlowableEmitter<T>) -> Unit) =
            create(source, BackpressureStrategy.BUFFER)

    @org.junit.Test fun testCreation() {
        val o0: Flowable<Int> = Flowable.empty()
        val list = bufferedFlowable<Int> { s ->
            s.onNext(1)
            s.onNext(777)
            s.onComplete()
        }.toList().blockingGet()
        assertEquals(listOf(1, 777), list)
        val o1: Flowable<Int> = listOf(1, 2, 3).toFlowable()
        val o2: Flowable<List<Int>> = Flowable.just(listOf(1, 2, 3))

        val o3: Flowable<Int> = Flowable.defer { bufferedFlowable<Int> { s -> s.onNext(1) } }
        val o4: Flowable<Int> = Array(3) { 0 }.toFlowable()
        val o5: Flowable<Int> = IntArray(3).toFlowable()

        assertNotNull(o0)
        assertNotNull(o1)
        assertNotNull(o2)
        assertNotNull(o3)
        assertNotNull(o4)
        assertNotNull(o5)
    }

    @org.junit.Test fun testExampleFromReadme() {
        val result = bufferedFlowable<String> { subscriber ->
            subscriber.onNext("H")
            subscriber.onNext("e")
            subscriber.onNext("l")
            subscriber.onNext("")
            subscriber.onNext("l")
            subscriber.onNext("o")
            subscriber.onComplete()
        }.filter(String::isNotEmpty).
                reduce(StringBuilder(), StringBuilder::append).
                map { it.toString() }.
                blockingGet()

        assertEquals("Hello", result)
    }

    @Test fun iteratorFlowable() {
        assertEquals(listOf(1, 2, 3), listOf(1, 2, 3).iterator().toFlowable().toList().blockingGet())
    }

    @Test fun intProgressionStep1Empty() {
        assertEquals(listOf(1), (1..1).toFlowable().toList().blockingGet())
    }

    @Test fun intProgressionStep1() {
        assertEquals((1..10).toList(), (1..10).toFlowable().toList().blockingGet())
    }

    @Test fun intProgressionDownTo() {
        assertEquals((1 downTo 10).toList(), (1 downTo 10).toFlowable().toList().blockingGet())
    }

    @Ignore("Too slow")
    @Test fun intProgressionOverflow() {
        assertEquals((0..10).toList().reversed(), (-10..Integer.MAX_VALUE).toFlowable().skip(Integer.MAX_VALUE.toLong()).map { Integer.MAX_VALUE - it }.toList().blockingGet())
    }


    @Test fun testFold() {
        val result = listOf(1, 2, 3).toFlowable().reduce(0) { acc, e -> acc + e }.blockingGet()
        assertEquals(6, result)
    }

    @Test fun `kotlin sequence should produce expected items and flowable be able to handle em`() {
        generateSequence(0) { it + 1 }.toFlowable()
                .take(3)
                .toList()
                .test()
                .assertValues(listOf(0, 1, 2))
    }

    @Test fun `infinite iterable should not hang or produce too many elements`() {
        val generated = AtomicInteger()
        generateSequence { generated.incrementAndGet() }.toFlowable().
                take(100).
                toList().
                subscribe()

        assertEquals(100, generated.get())
    }

    @Test fun testFlatMapSequence() {
        assertEquals(
                listOf(1, 2, 3, 2, 3, 4, 3, 4, 5),
                listOf(1, 2, 3).toFlowable().flatMapSequence { listOf(it, it + 1, it + 2).asSequence() }.toList().blockingGet()
        )
    }

    @Test fun testCombineLatest() {
        val list = listOf(1, 2, 3, 2, 3, 4, 3, 4, 5)
        assertEquals(list, list.map { Flowable.just(it) }.combineLatest { it }.blockingFirst())
    }

    @Test fun testZip() {
        val list = listOf(1, 2, 3, 2, 3, 4, 3, 4, 5)
        assertEquals(list, list.map { Flowable.just(it) }.zip { it }.blockingFirst())
    }

    @Test fun testCast() {
        val source = Flowable.just<Any>(1, 2)
        val flowable = source.cast<Int>()
        flowable.test()
                .await()
                .assertValues(1, 2)
                .assertNoErrors()
                .assertComplete()
    }

    @Test fun testCastWithWrongType() {
        val source = Flowable.just<Any>(1, 2)
        val flowable = source.cast<String>()
        flowable.test()
                .assertError(ClassCastException::class.java)
    }

    @Test fun combineLatestPair() {
        Flowable.just(3)
                .combineLatest(Flowable.just(10))
                .map { (x, y) -> x * y }
                .test()
                .assertValues(30)
    }

    @Test fun combineLatestTriple() {
        Flowable.just(3)
                .combineLatest(Flowable.just(10), Flowable.just(20))
                .map { (x, y, z) -> x * y * z }
                .test()
                .assertValues(600)
    }
    @Test
    fun testSubscribeBy() {
        val first = AtomicReference<String>()

        Flowable.just("Alpha")
                .subscribeBy {
                    first.set(it)
                }
        Assert.assertTrue(first.get() == "Alpha")
    }

    @Test
    fun testBlockingSubscribeBy() {
        val first = AtomicReference<String>()

        Flowable.just("Alpha")
                .blockingSubscribeBy {
                    first.set(it)
                }
        Assert.assertTrue(first.get() == "Alpha")
    }
    @Test
    fun testPairZip() {

        val testSubscriber = TestSubscriber<Pair<String,Int>>()

        Flowables.zip(
                Flowable.just("Alpha", "Beta", "Gamma"),
                Flowable.range(1,4)
        ).subscribe(testSubscriber)

        testSubscriber.assertValues(Pair("Alpha",1), Pair("Beta",2), Pair("Gamma",3))
    }

    @Test
    fun testTripleZip() {

        val testSubscriber = TestSubscriber<Triple<String,Int,Int>>()

        Flowables.zip(
                Flowable.just("Alpha", "Beta", "Gamma"),
                Flowable.range(1,4),
                Flowable.just(100,200,300)
        ).subscribe(testSubscriber)

        testSubscriber.assertValues(Triple("Alpha",1, 100), Triple("Beta",2, 200), Triple("Gamma",3, 300))
    }
}