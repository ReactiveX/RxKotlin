package rx.lang.kotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import rx.Single
import rx.functions.Action0
import java.util.*
import java.util.concurrent.Callable
import org.junit.Test as test

class CompletableTest {
/*
    @test fun testCreateFromAction() {
        var count = 0
        val c1 = Action0 { count++ }.toCompletable()
        assertNotNull(c1)
        c1.subscribe()
        assertEquals(1, count)

        count = 0
        val c2 = completableOf { count++ }
        assertNotNull(c2)
        c2.subscribe()
        assertEquals(1, count)
    }
*/
    @test fun testCreateFromCallable() {
        var count = 0
        val c1 = Callable { count++ }.toCompletable()
        assertNotNull(c1)
        c1.subscribe()
        assertEquals(1, count)
    }

    @test(expected = NoSuchElementException::class) fun testCreateFromFuture() {
        val c1 = 1.toSingletonObservable().toBlocking().toFuture().toCompletable()
        assertNotNull(c1)
        c1.toObservable<Int>().toBlocking().first()
    }

    @test(expected = NoSuchElementException::class) fun testCreateFromSingle() {
        val c1 = Single.just("Hello World!").toCompletable()
        assertNotNull(c1)
        c1.toObservable<String>().toBlocking().first()
    }

}
