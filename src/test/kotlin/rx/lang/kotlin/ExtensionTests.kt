/**
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rx.lang.kotlin

import rx.Observable
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Matchers.*
import org.junit.Assert.*
import rx.Notification
import kotlin.concurrent.thread
import rx.Subscriber
import org.funktionale.partials.*

/**
 * This class contains tests using the extension functions provided by the language adaptor.
 */
public class ExtensionTests : KotlinTests() {


    @Test
    public fun testCreate() {

        observable<String> { subscriber ->
            subscriber.onNext("Hello")
            subscriber.onCompleted()
        }.subscribe { result ->
            a.received(result)
        }

        verify(a, times(1)).received("Hello")
    }

    @Test
    public fun testFilter() {
        listOf(1, 2, 3).toObservable().filter { it >= 2 }.subscribe(received)
        verify(a, times(0)).received(1);
        verify(a, times(1)).received(2);
        verify(a, times(1)).received(3);
    }


    @Test
    public fun testLast() {
        assertEquals("three", listOf("one", "two", "three").toObservable().toBlocking().last())
    }

    @Test
    public fun testLastWithPredicate() {
        assertEquals("two", listOf("one", "two", "three").toObservable().toBlocking().last { x -> x.length() == 3 })
    }

    @Test
    public fun testMap1() {
        1.toSingletonObservable().map { v -> "hello_$v" }.subscribe((received))
        verify(a, times(1)).received("hello_1")
    }

    @Test
    public fun testMap2() {
        listOf(1, 2, 3).toObservable().map { v -> "hello_$v" }.subscribe((received))
        verify(a, times(1)).received("hello_1")
        verify(a, times(1)).received("hello_2")
        verify(a, times(1)).received("hello_3")
    }

    @Test
    public fun testMaterialize() {
        listOf(1, 2, 3).toObservable().materialize().subscribe((received))
        verify(a, times(4)).received(any(javaClass<Notification<Int>>()))
        verify(a, times(0)).error(any(javaClass<Exception>()))
    }


    @Test
    public fun testMerge() {
        listOf(listOf(1, 2, 3).toObservable(),
                listOf(6.toSingletonObservable(),
                        NullPointerException().toObservable<Int>(),
                        7.toSingletonObservable()
                ).merge(),
                listOf(4, 5).toObservable()
        ).merge().subscribe(received, { e -> a.error(e) })
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(1)).received(3)
        verify(a, times(0)).received(4)
        verify(a, times(0)).received(5)
        verify(a, times(1)).received(6)
        verify(a, times(0)).received(7)
        verify(a, times(1)).error(any(javaClass<NullPointerException>()))
    }

    @Test
    public fun testScriptWithMaterialize() {
        TestFactory().observable.materialize().subscribe((received))
        verify(a, times(2)).received(any(javaClass<Notification<Int>>()))
    }

    @Test
    public fun testScriptWithMerge() {
        val factory = TestFactory()
        (factory.observable mergeWith factory.observable).subscribe((received))
        verify(a, times(1)).received("hello_1")
        verify(a, times(1)).received("hello_2")
    }


    @Test
    public fun testFromWithIterable() {
        assertEquals(5, listOf(1, 2, 3, 4, 5).toObservable().count().toBlocking().single())
    }

    @Test
    public fun testStartWith() {
        val list = listOf(10, 11, 12, 13, 14)
        val startList = listOf(1, 2, 3, 4, 5)
        assertEquals(6, list.toObservable().startWith(0).count().toBlocking().single())
        assertEquals(10, list.toObservable().startWith(startList).count().toBlocking().single())
    }

    @Test
    public fun testScriptWithOnNext() {
        TestFactory().observable.subscribe((received))
        verify(a, times(1)).received("hello_1")
    }

    @Test
    public fun testSkipTake() {
        listOf(1, 2, 3).toObservable().skip(1).take(1).subscribe(received)
        verify(a, times(0)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test
    public fun testSkip() {
        listOf(1, 2, 3).toObservable().skip(2).subscribe(received)
        verify(a, times(0)).received(1)
        verify(a, times(0)).received(2)
        verify(a, times(1)).received(3)
    }

    @Test
    public fun testTake() {
        listOf(1, 2, 3).toObservable().take(2).subscribe(received)
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test
    public fun testTakeLast() {
        TestFactory().observable.takeLast(1).subscribe((received))
        verify(a, times(1)).received("hello_1")
    }

    @Test
    public fun testTakeWhile() {
        listOf(1, 2, 3).toObservable().takeWhile { x -> x < 3 }.subscribe(received)
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test
    public fun testTakeWhileWithIndex() {
        listOf(1, 2, 3).toObservable().takeWhile { x -> x < 3 }.zipWith((0..Integer.MAX_VALUE).toObservable()) { x, i -> x }.subscribe(received)
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test
    public fun testToSortedList() {
        TestFactory().numbers.toSortedList().subscribe(received)
        verify(a, times(1)).received(listOf(1, 2, 3, 4, 5))
    }

    @Test
    public fun testForEach() {
        observable(asyncObservable).toBlocking().forEach(received)
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(1)).received(3)
    }

    @Test(expected = RuntimeException::class)
    public fun testForEachWithError() {
        observable(asyncObservable).toBlocking().forEach { throw RuntimeException("err") }
        fail("we expect an exception to be thrown")
    }

    @Test
    public fun testLastOrDefault() {
        assertEquals("two", listOf("one", "two").toObservable().toBlocking().lastOrDefault("default") { x -> x.length() == 3 })
        assertEquals("default", listOf("one", "two").toObservable().toBlocking().lastOrDefault("default") { x -> x.length() > 3 })
    }

    @Test
    public fun testDefer() {
        deferredObservable { listOf(1, 2).toObservable() }.subscribe(received)
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
    }

    @Test
    public fun testAll() {
        listOf(1, 2, 3).toObservable().all { x -> x > 0 }.subscribe(received)
        verify(a, times(1)).received(true)
    }

    @Test
    public fun testZip() {
        val o1 = listOf(1, 2, 3).toObservable()
        val o2 = listOf(4, 5, 6).toObservable()
        val o3 = listOf(7, 8, 9).toObservable()

        val values = Observable.zip(o1, o2, o3) { a, b, c -> listOf(a, b, c) }.toList().toBlocking().single()
        assertEquals(listOf(1, 4, 7), values[0])
        assertEquals(listOf(2, 5, 8), values[1])
        assertEquals(listOf(3, 6, 9), values[2])
    }

    val funOnSubscribe: (Int, Subscriber<in String>) -> Unit = { counter, subscriber ->
        subscriber.onNext("hello_$counter")
        subscriber.onCompleted()
    }

    val asyncObservable: (Subscriber<in Int>) -> Unit = { subscriber ->
        thread {
            Thread.sleep(50)
            subscriber.onNext(1)
            subscriber.onNext(2)
            subscriber.onNext(3)
            subscriber.onCompleted()
        }
    }



    inner public class TestFactory() {
        var counter = 1

        val numbers: Observable<Int>
            get() = listOf(1, 3, 2, 5, 4).toObservable()

        val onSubscribe: (Subscriber<in String>) -> Unit
            get() = funOnSubscribe(p1 = counter++) // partial applied function

        val observable: Observable<String>
            get() = observable(onSubscribe)

    }
}
