package io.reactivex.rxkotlin

import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class FlowableTest {

    private fun <T: Any>  bufferedFlowable(source: (io.reactivex.FlowableEmitter<T>) -> Unit) =
            io.reactivex.Flowable.create(source, io.reactivex.BackpressureStrategy.BUFFER)

    @org.junit.Test fun testCreation() {
        val o0: io.reactivex.Flowable<Int> = io.reactivex.Flowable.empty()
        val list = bufferedFlowable<Int> { s ->
            s.onNext(1)
            s.onNext(777)
            s.onComplete()
        }.toList().blockingGet()
        org.junit.Assert.assertEquals(listOf(1, 777), list)
        val o1: io.reactivex.Flowable<Int> = listOf(1, 2, 3).toFlowable()
        val o2: io.reactivex.Flowable<List<Int>> = io.reactivex.Flowable.just(listOf(1, 2, 3))

        val o3: io.reactivex.Flowable<Int> = io.reactivex.Flowable.defer { bufferedFlowable<Int> { s -> s.onNext(1) } }
        val o4: io.reactivex.Flowable<Int> = Array(3) { 0 }.toFlowable()
        val o5: io.reactivex.Flowable<Int> = IntArray(3).toFlowable()

        org.junit.Assert.assertNotNull(o0)
        org.junit.Assert.assertNotNull(o1)
        org.junit.Assert.assertNotNull(o2)
        org.junit.Assert.assertNotNull(o3)
        org.junit.Assert.assertNotNull(o4)
        org.junit.Assert.assertNotNull(o5)
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

        Assert.assertEquals("Hello", result)
    }

    @Test fun iteratorFlowable() {
        Assert.assertEquals(listOf(1, 2, 3), listOf(1, 2, 3).iterator().toFlowable().toList().blockingGet())
    }

    @Test fun intProgressionStep1Empty() {
        Assert.assertEquals(listOf(1), (1..1).toFlowable().toList().blockingGet())
    }

    @Test fun intProgressionStep1() {
        Assert.assertEquals((1..10).toList(), (1..10).toFlowable().toList().blockingGet())
    }

    @Test fun intProgressionDownTo() {
        Assert.assertEquals((1 downTo 10).toList(), (1 downTo 10).toFlowable().toList().blockingGet())
    }

    @Ignore("Too slow")
    @Test fun intProgressionOverflow() {
        Assert.assertEquals((0..10).toList().reversed(), (-10..Integer.MAX_VALUE).toFlowable().skip(Integer.MAX_VALUE.toLong()).map { Integer.MAX_VALUE - it }.toList().blockingGet())
    }


    @Test fun testFold() {
        val result = listOf(1, 2, 3).toFlowable().reduce(0) { acc, e -> acc + e }.blockingGet()
        Assert.assertEquals(6, result)
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

        Assert.assertEquals(100, generated.get())
    }

    @Test fun testFlatMapSequence() {
        Assert.assertEquals(
                listOf(1, 2, 3, 2, 3, 4, 3, 4, 5),
                listOf(1, 2, 3).toFlowable().flatMapSequence { listOf(it, it + 1, it + 2).asSequence() }.toList().blockingGet()
        )
    }

    @Test fun testCombineLatest() {
        val list = listOf(1, 2, 3, 2, 3, 4, 3, 4, 5)
        Assert.assertEquals(list, list.map { Flowable.just(it) }.combineLatest { it }.blockingFirst())
    }

    @Test fun testZip() {
        val list = listOf(1, 2, 3, 2, 3, 4, 3, 4, 5)
        Assert.assertEquals(list, list.map { Flowable.just(it) }.zip { it }.blockingFirst())
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

    @Test fun testWithLatestFrom() {
        val emit1 = Flowable.just(1)
        emit1.withLatestFrom(emit1) { a, b -> a + b }.test().assertValue(2)
        emit1.withLatestFrom(emit1, emit1) { a, b, c -> a + b + c }.test().assertValue(3)
        emit1.withLatestFrom(emit1, emit1, emit1) { a, b, c, d -> a + b + c + d }.test().assertValue(4)
        emit1.withLatestFrom(emit1, emit1, emit1, emit1) { a, b, c, d, e -> a + b + c + d + e }.test().assertValue(5)
    }

    @Test fun testZipWith() {
        Flowable.fromArray(1, 2).zipWith(Flowable.fromArray(3, 4)) { a, b -> a + b }.test().assertValues(4, 6)
    }
}