package rx.lang.kotlin

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Ignore
import java.util.concurrent.atomic.AtomicInteger
import org.junit.Test as test


class ObservablesTest {
    @test fun testCreation() {
        val o0: Observable<Int> = emptyObservable()
        val list = observable<Int> { s ->
            s.onNext(1)
            s.onNext(777)
            s.onComplete()
        }.toList().blockingGet()
        assertEquals(listOf(1, 777), list)
        val o1: Observable<Int> = listOf(1, 2, 3).toObservable()
        val o2: Observable<List<Int>> = listOf(1, 2, 3).toSingletonObservable()
        
        val o3: Observable<Int> = observable<Int> { s -> s.onNext(1) }.defer()
        val o4: Observable<Int> = Array(3) { 0 }.toObservable()
        val o5: Observable<Int> = IntArray(3).toObservable()
        
        assertNotNull(o0)
        assertNotNull(o1)
        assertNotNull(o2)
        assertNotNull(o3)
        assertNotNull(o4)
        assertNotNull(o5)
    }
    
    @test fun testExampleFromReadme() {
        val result = observable<String> { subscriber ->
            subscriber.onNext("H")
            subscriber.onNext("e")
            subscriber.onNext("l")
            subscriber.onNext("")
            subscriber.onNext("l")
            subscriber.onNext("o")
            subscriber.onComplete()
        }.filter(String::isNotEmpty).
                fold(StringBuilder(), StringBuilder::append).
                map { it.toString() }.
                blockingGet()
        
        assertEquals("Hello", result)
    }
    
    @test fun iteratorObservable() {
        assertEquals(listOf(1, 2, 3), listOf(1, 2, 3).iterator().toObservable().toList().blockingGet())
    }
    
    @test fun intProgressionStep1Empty() {
        assertEquals(listOf(1), (1..1).toObservable().toList().blockingGet())
    }
    
    @test fun intProgressionStep1() {
        assertEquals((1..10).toList(), (1..10).toObservable().toList().blockingGet())
    }
    
    @test fun intProgressionDownTo() {
        assertEquals((1 downTo 10).toList(), (1 downTo 10).toObservable().toList().blockingGet())
    }
    
    @Ignore("Too slow")
    @test fun intProgressionOverflow() {
        assertEquals((0..10).toList().reversed(), (-10..Integer.MAX_VALUE).toObservable().skip(Integer.MAX_VALUE.toLong()).map { Integer.MAX_VALUE - it }.toList().blockingGet())
    }
    
    @test fun testWithIndex() {
        listOf("a", "b", "c").toObservable()
                .withIndex()
                .toList()
                .test()
                .assertValues(listOf(IndexedValue(0, "a"), IndexedValue(1, "b"), IndexedValue(2, "c")))
    }
    
    @test fun `withIndex() shouldn't share index between multiple subscribers`() {
        val o = listOf("a", "b", "c").toObservable().withIndex()
        
        val subscriber1 = TestObserver.create<IndexedValue<String>>()
        val subscriber2 = TestObserver.create<IndexedValue<String>>()
        
        o.subscribe(subscriber1)
        o.subscribe(subscriber2)
        
        subscriber1.awaitTerminalEvent()
        subscriber1.assertValues(IndexedValue(0, "a"), IndexedValue(1, "b"), IndexedValue(2, "c"))
        
        subscriber2.awaitTerminalEvent()
        subscriber2.assertValues(IndexedValue(0, "a"), IndexedValue(1, "b"), IndexedValue(2, "c"))
    }
    
    @test fun testFold() {
        val result = listOf(1, 2, 3).toObservable().fold(0) { acc, e -> acc + e }.blockingGet()
        assertEquals(6, result)
    }
    
    @test fun `kotlin sequence should produce expected items and observable be able to handle em`() {
        generateSequence(0) { it + 1 }.toObservable()
                .take(3)
                .toList()
                .test()
                .assertValues(listOf(0, 1, 2))
    }
    
    @test fun `infinite iterable should not hang or produce too many elements`() {
        val generated = AtomicInteger()
        generateSequence { generated.incrementAndGet() }.toObservable().
                take(100).
                toList().
                subscribe()
        
        assertEquals(100, generated.get())
    }
    
    @test fun testFlatMapSequence() {
        assertEquals(
                listOf(1, 2, 3, 2, 3, 4, 3, 4, 5),
                listOf(1, 2, 3).toObservable().flatMapSequence { listOf(it, it + 1, it + 2).asSequence() }.toList().blockingGet()
        )
    }
    
    @test fun testCombineLatest() {
        val list = listOf(1, 2, 3, 2, 3, 4, 3, 4, 5)
        assertEquals(list, list.map { it.toSingletonObservable() }.combineLatest { it }.blockingFirst())
    }
    
    @test fun testZip() {
        val list = listOf(1, 2, 3, 2, 3, 4, 3, 4, 5)
        assertEquals(list, list.map { it.toSingletonObservable() }.zip { it }.blockingFirst())
    }
    
    @test fun testCast() {
        val source = Observable.just<Any>(1, 2)
        val observable = source.cast<Int>()
        observable.test()
                .await()
                .assertValues(1, 2)
                .assertNoErrors()
                .assertComplete()
    }
    
    @test fun testCastWithWrongType() {
        val source = Observable.just<Any>(1, 2)
        val observable = source.cast<String>()
        observable.test()
                .assertError(ClassCastException::class.java)
    }
}