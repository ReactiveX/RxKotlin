package rx.lang.kotlin

import io.reactivex.Single
import io.reactivex.functions.Action
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.NoSuchElementException
import java.util.concurrent.Callable

class CompletableTest {

    @Test fun testCreateFromAction() {
        var count = 0
        val c1 = Action { count++ }.toCompletable()
        assertNotNull(c1)
        c1.subscribe()
        assertEquals(1, count)
    }

    @Test fun testCreateFromCallable() {
        var count = 0
        val c1 = Callable { count++ }.toCompletable()
        assertNotNull(c1)
        c1.subscribe()
        assertEquals(1, count)

        count = 0
        val c2 = { count++ }.toCompletable()
        assertNotNull(c2)
        c2.subscribe()
        assertEquals(1, count)
    }

    @Test(expected = NoSuchElementException::class) fun testCreateFromSingle() {
        val c1 = Single.just("Hello World!").toCompletable()
        assertNotNull(c1)
        c1.toObservable<String>().blockingFirst()
    }

}
