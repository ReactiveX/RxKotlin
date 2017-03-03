package rx.lang.kotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import rx.Observable
import rx.Single
import rx.functions.Action0
import java.util.NoSuchElementException
import java.util.concurrent.Callable

class CompletableTest {

    @Test fun testCreateFromAction() {
        var count = 0
        val c1 = Action0 { count++ }.toCompletable()
        c1.subscribe()
        assertEquals(1, count)
    }

    @Test fun testCreateFromCallable() {
        var count = 0
        val c1 = Callable { count++ }.toCompletable()
        c1.subscribe()
        assertEquals(1, count)
    }

    @Test fun testCreateFromFunction() {
        var count = 0
        val c1 = { count++ }.toCompletable()
        c1.subscribe()
        assertEquals(1, count)
    }

    @Test(expected = NoSuchElementException::class)
    fun testCreateFromFuture() {
        val c1 = Observable.just(1).toBlocking().toFuture().toCompletable()
        c1.toObservable<Int>().toBlocking().first()
    }

    @Test(expected = NoSuchElementException::class)
    fun testCreateFromSingle() {
        val c1 = Single.just("Hello World!").toCompletable()
        assertNotNull(c1)
        c1.toObservable<String>().toBlocking().first()
    }


}
