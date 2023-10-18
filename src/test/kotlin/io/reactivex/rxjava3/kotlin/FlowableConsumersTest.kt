/*
 * Copyright (c) 2021-present, RxKotlin Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.LambdaConsumerIntrospection
import io.reactivex.rxjava3.processors.PublishProcessor
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class FlowableConsumersTest {

    private fun CompositeDisposable.isEmpty(): Boolean = size() == 0
    private fun CompositeDisposable.isNotEmpty(): Boolean = size() > 0

    private val disposables = CompositeDisposable()
    private val processor = PublishProcessor.create<Int>()
    private val events = mutableListOf<Any>()

    @Test
    fun errorIntrospectionNormal() {
        val disposable = processor.subscribeBy(disposables) as LambdaConsumerIntrospection
        assertFalse(disposable.hasCustomOnError())
    }

    @Test
    fun errorIntrospectionCustom() {
        val disposable = processor.subscribeBy(disposables, onError = {}) as LambdaConsumerIntrospection
        assertTrue(disposable.hasCustomOnError())
    }

    @Test
    fun onNextNormal() {
        processor.subscribeBy(
            disposables,
            onNext = events::add
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        processor.onNext(1)

        assertTrue(disposables.isNotEmpty())
        assertEquals(listOf(1), events)

        processor.onComplete()

        assertTrue(disposables.isEmpty())
        assertEquals(listOf(1), events)
    }

    @Test
    fun onErrorNormal() {
        processor.subscribeBy(
            disposables,
            onNext = events::add,
            onError = events::add
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        processor.onNext(1)

        assertTrue(disposables.isNotEmpty())
        assertEquals(listOf(1), events)

        processor.onComplete()

        assertTrue(disposables.isEmpty())
        assertEquals(listOf(1), events)
    }

    @Test
    fun onErrorError() {
        processor.subscribeBy(
            disposables,
            onNext = events::add,
            onError = events::add
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        processor.onNext(1)

        assertTrue(disposables.isNotEmpty())
        assertEquals(listOf(1), events)

        processor.onError(IOException())

        assertTrue(disposables.isEmpty())
        assertEquals(2, events.size)
        assertEquals(1, events[0])
        assertTrue(events[1] is IOException)
    }

    @Test
    fun onCompleteNormal() {
        processor.subscribeBy(
            disposables,
            onNext = events::add,
            onError = events::add,
            onComplete = { events.add("completed") }
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        processor.onNext(1)

        assertTrue(disposables.isNotEmpty())
        assertEquals(listOf(1), events)

        processor.onComplete()

        assertTrue(disposables.isEmpty())
        assertEquals(listOf(1, "completed"), events)
    }

    @Test
    fun onCompleteError() {
        processor.subscribeBy(
            disposables,
            onNext = events::add,
            onError = events::add,
            onComplete = { events.add("completed") }
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        processor.onNext(1)

        assertTrue(disposables.isNotEmpty())
        assertEquals(listOf(1), events)

        processor.onError(IOException())

        assertTrue(disposables.isEmpty())
        assertEquals(2, events.size)
        assertEquals(1, events[0])
        assertTrue(events[1] is IOException)
    }

    @Test
    fun onCompleteDispose() {
        val disposable = processor.subscribeBy(
            disposables,
            onNext = events::add,
            onError = events::add,
            onComplete = { events.add("completed") }
        )

        assertFalse(disposable.isDisposed)
        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        disposable.dispose()

        assertTrue(disposable.isDisposed)
        assertTrue(disposables.isEmpty())
        assertTrue(events.isEmpty())
    }
}
