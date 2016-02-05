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

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.mockito.Mockito.*
import rx.Notification
import rx.Observable
import rx.Observable.OnSubscribe
import rx.Subscriber
import kotlin.concurrent.thread

/**
 * This class use plain Kotlin without extensions from the language adaptor
 */
class BasicKotlinTests : KotlinTests() {


    @Test fun testCreate() {
        Observable.create(OnSubscribe<kotlin.String> { subscriber ->
            subscriber.onNext("Hello")
            subscriber.onCompleted()
        }).subscribe { result ->
            a.received(result)
        }

        verify(a, times(1)).received("Hello")
    }

    @Test fun testFilter() {
        Observable.from(listOf(1, 2, 3)).filter { it >= 2 }.subscribe(received())
        verify(a, times(0)).received(1);
        verify(a, times(1)).received(2);
        verify(a, times(1)).received(3);
    }

    @Test fun testLast() {
        assertEquals("three", Observable.from(listOf("one", "two", "three")).toBlocking().last())
    }

    @Test fun testLastWithPredicate() {
        assertEquals("two", Observable.from(listOf("one", "two", "three")).toBlocking().last { x -> x.length == 3 })
    }

    @Test fun testMap1() {
        Observable.just(1).map { v -> "hello_$v" }.subscribe(received())
        verify(a, times(1)).received("hello_1")
    }

    @Test fun testMap2() {
        Observable.from(listOf(1, 2, 3)).map { v -> "hello_$v" }.subscribe(received())
        verify(a, times(1)).received("hello_1")
        verify(a, times(1)).received("hello_2")
        verify(a, times(1)).received("hello_3")
    }

    @Test fun testMaterialize() {
        Observable.from(listOf(1, 2, 3)).materialize().subscribe(received())
        verify(a, times(4)).received(any(Notification::class.java))
        verify(a, times(0)).error(any(Exception::class.java))
    }

    @Test fun testMerge() {
        Observable.merge(
                Observable.from(listOf(1, 2, 3)),
                Observable.merge(
                        Observable.just(6),
                        Observable.error(NullPointerException()),
                        Observable.just(7)
                ),
                Observable.from(listOf(4, 5))
        ).subscribe(received()) { e -> a.error(e) }
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(1)).received(3)
        verify(a, times(0)).received(4)
        verify(a, times(0)).received(5)
        verify(a, times(1)).received(6)
        verify(a, times(0)).received(7)
        verify(a, times(1)).error(any(NullPointerException::class.java))
    }

    @Test fun testScriptWithMaterialize() {
        TestFactory().observable.materialize().subscribe(received())
        verify(a, times(2)).received(any(Notification::class.java))
    }

    @Test fun testScriptWithMerge() {
        val factory = TestFactory()
        Observable.merge(factory.observable, factory.observable).subscribe(received())
        verify(a, times(1)).received("hello_1")
        verify(a, times(1)).received("hello_2")
    }

    @Test fun testFromWithIterable() {
        val list = listOf(1, 2, 3, 4, 5)
        assertEquals(5, Observable.from(list).count().toBlocking().single())
    }

    @Test fun testFromWithObjects() {
        val list = listOf(1, 2, 3, 4, 5)
        assertEquals(2, Observable.from(listOf(list, 6)).count().toBlocking().single())
    }

    @Test fun testStartWith() {
        val list = listOf(10, 11, 12, 13, 14)
        val startList = listOf(1, 2, 3, 4, 5)
        assertEquals(6, Observable.from(list).startWith(0).count().toBlocking().single())
        assertEquals(10, Observable.from(list).startWith(startList).count().toBlocking().single())
    }

    @Test fun testScriptWithOnNext() {
        TestFactory().observable.subscribe(received())
        verify(a, times(1)).received("hello_1")
    }

    @Test fun testSkipTake() {
        Observable.from(listOf(1, 2, 3)).skip(1).take(1).subscribe(received())
        verify(a, times(0)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test fun testSkip() {
        Observable.from(listOf(1, 2, 3)).skip(2).subscribe(received())
        verify(a, times(0)).received(1)
        verify(a, times(0)).received(2)
        verify(a, times(1)).received(3)
    }

    @Test fun testTake() {
        Observable.from(listOf(1, 2, 3)).take(2).subscribe(received())
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test fun testTakeLast() {
        TestFactory().observable.takeLast(1).subscribe(received())
        verify(a, times(1)).received("hello_1")
    }

    @Test fun testTakeWhile() {
        Observable.from(listOf(1, 2, 3)).takeWhile { x -> x < 3 }.subscribe(received())
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test fun testTakeWhileWithIndex() {
        Observable.from(listOf(1, 2, 3)).takeWhile { x -> x < 3 }.zipWith(Observable.range(0,Integer.MAX_VALUE)){ x, i -> x }.subscribe(received())
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(0)).received(3)
    }

    @Test fun testToSortedList() {
        TestFactory().numbers.toSortedList().subscribe(received())
        verify(a, times(1)).received(listOf(1, 2, 3, 4, 5))
    }

    @Test fun testForEach() {
        Observable.create(AsyncObservable()).toBlocking().forEach(received())
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
        verify(a, times(1)).received(3)
    }

    @Test(expected = RuntimeException::class) fun testForEachWithError() {
        Observable.create(AsyncObservable()).toBlocking().forEach { throw RuntimeException("err") }
        fail("we expect an exception to be thrown")
    }

    @Test fun testLastOrDefault() {
        assertEquals("two", Observable.from(listOf("one", "two")).toBlocking().lastOrDefault("default") { x -> x.length == 3 })
        assertEquals("default", Observable.from(listOf("one", "two")).toBlocking().lastOrDefault("default") { x -> x.length > 3 })
    }

    @Test(expected = IllegalArgumentException::class) fun testSingle() {
        assertEquals("one", Observable.just("one").toBlocking().single { x -> x.length == 3 })
        Observable.from(listOf("one", "two")).toBlocking().single { x -> x.length == 3 }
        fail()
    }

    @Test fun testDefer() {
        Observable.defer { Observable.from(listOf(1, 2)) }.subscribe(received())
        verify(a, times(1)).received(1)
        verify(a, times(1)).received(2)
    }

    @Test fun testAll() {
        Observable.from(listOf(1, 2, 3)).all { x -> x > 0 }.subscribe(received())
        verify(a, times(1)).received(true)
    }

    @Test fun testZip() {
        val o1 = Observable.from(listOf(1, 2, 3))
        val o2 = Observable.from(listOf(4, 5, 6))
        val o3 = Observable.from(listOf(7, 8, 9))

        val values = Observable.zip(o1, o2, o3) { a, b, c -> listOf(a, b, c) }.toList().toBlocking().single()
        assertEquals(listOf(1, 4, 7), values[0])
        assertEquals(listOf(2, 5, 8), values[1])
        assertEquals(listOf(3, 6, 9), values[2])
    }

    @Test fun testZipWithIterable() {
        val o1 = Observable.from(listOf(1, 2, 3))
        val o2 = Observable.from(listOf(4, 5, 6))
        val o3 = Observable.from(listOf(7, 8, 9))

        val values = Observable.zip(listOf(o1, o2, o3)) { args -> listOf(*args) }.toList().toBlocking().single()
        assertEquals(listOf(1, 4, 7), values[0])
        assertEquals(listOf(2, 5, 8), values[1])
        assertEquals(listOf(3, 6, 9), values[2])
    }

    @Test fun testGroupBy() {
        var count = 0

        Observable.from(listOf("one", "two", "three", "four", "five", "six"))
                .groupBy { s -> s.length }
                .flatMap { groupObervable ->
                    groupObervable.map { s ->
                        "Value: $s Group ${groupObervable.key}"
                    }
                }.toBlocking().forEach { s ->
            println(s)
            count++
        }

        assertEquals(6, count)
    }



    class TestFactory() {
        var counter = 1

        val numbers: Observable<Int>
            get(){
                return Observable.from(listOf(1, 3, 2, 5, 4))
            }

        val onSubscribe: TestOnSubscribe
            get(){
                return TestOnSubscribe(counter++)
            }

        val observable: Observable<String>
            get(){
                return Observable.create(onSubscribe)
            }

    }

    class AsyncObservable : OnSubscribe<Int> {
        override fun call(op: Subscriber<in Int>) {
            thread {
                Thread.sleep(50)
                op.onNext(1)
                op.onNext(2)
                op.onNext(3)
                op.onCompleted()
            }

        }
    }

    class TestOnSubscribe(val count: Int) : OnSubscribe<String> {
        override fun call(op: Subscriber<in String>) {
            op.onNext("hello_$count")
            op.onCompleted()
        }

    }
}
