package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.LambdaConsumerIntrospection
import io.reactivex.rxjava3.subjects.SingleSubject
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException

class SingleConsumersTest {

    private fun CompositeDisposable.isEmpty(): Boolean = size() == 0
    private fun CompositeDisposable.isNotEmpty(): Boolean = size() > 0

    private val disposables = CompositeDisposable()
    private val subject = SingleSubject.create<Int>()
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
    fun onCompleteDispose() {
        val disposable = subject.subscribeBy(
            disposables,
            onSuccess = events::add,
            onError = events::add
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
