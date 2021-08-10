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
import io.reactivex.rxjava3.subjects.MaybeSubject
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class MaybeConsumersTest {

    private fun CompositeDisposable.isEmpty(): Boolean = size() == 0
    private fun CompositeDisposable.isNotEmpty(): Boolean = size() > 0

    private val disposables = CompositeDisposable()
    private val subject = MaybeSubject.create<Int>()
    private val events = mutableListOf<Any>()

    @Test
    fun errorIntrospectionNormal() {
        val disposable = subject.subscribeBy(disposables) as LambdaConsumerIntrospection
        assertFalse(disposable.hasCustomOnError())
    }

    @Test
    fun errorIntrospectionCustom() {
        val disposable = subject.subscribeBy(disposables, onError = {}) as LambdaConsumerIntrospection
        assertTrue(disposable.hasCustomOnError())
    }

    @Test
    fun onSuccessNormal() {
        subject.subscribeBy(
            disposables,
            onSuccess = events::add
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        subject.onSuccess(1)

        assertTrue(disposables.isEmpty())
        assertEquals(listOf(1), events)
    }

    @Test
    fun onErrorNormal() {
        subject.subscribeBy(
            disposables,
            onSuccess = events::add,
            onError = events::add
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        subject.onSuccess(1)

        assertTrue(disposables.isEmpty())
        assertEquals(listOf(1), events)
    }

    @Test
    fun onErrorError() {
        subject.subscribeBy(
            disposables,
            onSuccess = events::add,
            onError = events::add
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        subject.onError(IOException())

        assertTrue(disposables.isEmpty())
        assertEquals(1, events.size)
        assertTrue(events[0] is IOException)
    }

    @Test
    fun onCompleteNormal() {
        subject.subscribeBy(
            disposables,
            onSuccess = events::add,
            onError = events::add,
            onComplete = { events.add("completed") }
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        subject.onComplete()

        assertTrue(disposables.isEmpty())
        assertEquals(listOf("completed"), events)
    }

    @Test
    fun onCompleteError() {
        subject.subscribeBy(
            disposables,
            onSuccess = events::add,
            onError = events::add,
            onComplete = { events.add("completed") }
        )

        assertTrue(disposables.isNotEmpty())
        assertTrue(events.isEmpty())

        subject.onError(IOException())

        assertTrue(disposables.isEmpty())
        assertEquals(1, events.size)
        assertTrue(events[0] is IOException)
    }

    @Test
    fun onCompleteDispose() {
        val disposable = subject.subscribeBy(
            disposables,
            onSuccess = events::add,
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
