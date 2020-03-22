package io.reactivex.rxkotlin

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.LambdaConsumerIntrospection
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class SingleTest : KotlinTests() {
    @Test fun testCreate() {
        Single.create<String> { s ->
            s.onSuccess("Hello World!")
        }.subscribe { result ->
            a.received(result)
        }
        verify(a, Mockito.times(1)).received("Hello World!")
    }

    @Test
    fun testSubscribeBy() {
        Single.just("Alpha")
                .subscribeBy {
                    a.received(it)
                }
        verify(a, Mockito.times(1))
                .received("Alpha")
    }

    @Test
    fun testSubscribeByErrorIntrospection() {
        val disposable = Single.just(Unit)
                .subscribeBy() as LambdaConsumerIntrospection
        Assert.assertFalse(disposable.hasCustomOnError())
    }

    @Test
    fun testSubscribeByErrorIntrospectionCustom() {
        val disposable = Single.just(Unit)
                .subscribeBy(onError = {}) as LambdaConsumerIntrospection
        Assert.assertTrue(disposable.hasCustomOnError())
    }

    @Test fun testConcatAll() {
        (0 until 10)
                .map { Single.just(it) }
                .concatAll()
                .toList()
                .subscribe { result ->
                    assertEquals((0 until 10).toList(), result)
                }
    }

    @Test fun testMergeAllSinglesForObservable() {
        val initialData = Observable.just(
            Single.just(1),
            Single.just(2),
            Single.just(3),
            Single.just(4)
        )

        val expected = mutableListOf<Int>().let { list ->
            initialData.flatMapSingle { it }.blockingIterable().forEach { list.add(it) }
        }
        val actual = mutableListOf<Int>().let {list ->
            initialData.mergeAllSingles().blockingIterable().forEach { list.add(it) }
        }

        assertEquals(expected, actual)
    }

    @Test fun testMergeAllSinglesForFlowable() {
        val initialData = Flowable.just(
            Single.just(1),
            Single.just(2),
            Single.just(3),
            Single.just(4)
        )

        val expected = mutableListOf<Int>().let { list ->
            initialData.flatMapSingle { it }.blockingIterable().forEach { list.add(it) }
        }
        val actual = mutableListOf<Int>().let {list ->
            initialData.mergeAllSingles().blockingIterable().forEach { list.add(it) }
        }

        assertEquals(expected, actual)
    }
}
