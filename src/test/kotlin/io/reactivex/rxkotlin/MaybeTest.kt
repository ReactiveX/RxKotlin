package io.reactivex.rxkotlin

import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.atomic.AtomicReference

class MaybeTest {
    @Test
    fun testSubscribeBy() {
        val first = AtomicReference<String>()

        Maybe.just("Alpha")
                .subscribeBy {
                    first.set(it)
                }
        Assert.assertTrue(first.get() == "Alpha")
    }

    @Test fun testConcatAll() {
        (0 until 10)
                .map { Maybe.just(it) }
                .concatAll()
                .toList()
                .subscribe { result ->
                    Assert.assertEquals((0 until 10).toList(), result)
                }
    }
}