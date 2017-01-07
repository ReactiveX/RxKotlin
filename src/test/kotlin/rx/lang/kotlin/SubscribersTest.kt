package rx.lang.kotlin

import io.reactivex.Observer
import io.reactivex.disposables.Disposables
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.util.ArrayList
import org.junit.Test as test

class SubscribersTest {
    @test fun testEmptySubscriber() {
        val s = subscriber<Int>()
        callSubscriberMethods(false, s)
    }
    
    @test fun testSubscriberConstruction() {
        val events = ArrayList<String>()
        
        callSubscriberMethods(false, subscriber<Int>().
                onNext { events.add("onNext($it)") }
        )
        
        assertEquals(listOf("onNext(1)"), events)
        events.clear()
        
        callSubscriberMethods(true, subscriber<Int>().
                onNext { events.add("onNext($it)") }.
                onError { events.add(it.javaClass.simpleName) }
        )
        
        assertEquals(listOf("onNext(1)", "RuntimeException"), events)
        events.clear()
        
        callSubscriberMethods(true, subscriber<Int>().
                onNext { events.add("onNext($it)") }.
                onError { events.add(it.javaClass.simpleName) }.
                onCompleted { events.add("onCompleted") }
        )
        
        assertEquals(listOf("onNext(1)", "RuntimeException", "onCompleted"), events)
        events.clear()
    }
    
    @test(expected = Exception::class)
    fun testNoErrorHandlers() {
        subscriber<Int>().onError(Exception("expected"))
    }
    
    @test fun testErrorHandlers() {
        var errorsCaught = 0
        
        subscriber<Int>().
                onError { errorsCaught++ }.
                onError { errorsCaught++ }.
                onError(Exception("expected"))
        
        assertEquals(2, errorsCaught)
    }
    
    @test fun testMultipleOnNextHandlers() {
        var nextCaught = 0
        
        subscriber<Int>().
                onNext { nextCaught++ }.
                onNext { nextCaught++ }.
                onNext(1)
        
        assertEquals(2, nextCaught)
    }
    
    @test fun testOnStart() {
        var started = false
        "".toSingletonObservable().subscribeBy {
            onStart { started = true }
        }
        assertTrue(started)
    }
    
    private fun callSubscriberMethods(hasOnError: Boolean, s: Observer<Int>) {
        s.onSubscribe(Disposables.empty())
        s.onNext(1)
        try {
            s.onError(RuntimeException())
        } catch (t: Throwable) {
            if (hasOnError) throw t
        }
        s.onComplete()
    }
    
    @test fun testSubscribeWith() {
        val completeObservable = observable<Int> {
            it.onNext(1)
            it.onComplete()
        }
        
        val events = ArrayList<String>()
        
        completeObservable.subscribeBy {
            onNext { events.add("onNext($it)") }
        }
        
        assertEquals(listOf("onNext(1)"), events)
        events.clear()
        
        completeObservable.subscribeBy {
            onNext { events.add("onNext($it)") }
            onComplete { events.add("onCompleted") }
        }
        
        assertEquals(listOf("onNext(1)", "onCompleted"), events)
        events.clear()
        
        val errorObservable = observable<Int> {
            it.onNext(1)
            it.onError(RuntimeException())
        }
        
        errorObservable.subscribeBy {
            onNext { events.add("onNext($it)") }
            onError { events.add("onError(${it.javaClass.simpleName})") }
        }
        
        assertEquals(listOf("onNext(1)", "onError(RuntimeException)"), events)
    }
    
    @test fun testSingleSubscribeWith() {
        val events = ArrayList<String>()
        val successSingle = singleOf(1)
        
        successSingle.subscribeBy {
            onSuccess { events.add("onSuccess($it)") }
        }
        
        assertEquals(listOf("onSuccess(1)"), events)
        events.clear()
        
        val errorSingle = RuntimeException().toSingle<Int>()
        
        errorSingle.subscribeBy {
            onSuccess { events.add("onSuccess($it)") }
            onError { events.add("onError(${it.javaClass.simpleName})") }
        }
        
        assertEquals(listOf("onError(RuntimeException)"), events)
    }
    
    @test fun testSubscribeByErrorNotImplemented() {
        val events = ArrayList<String>()
        val errorSingle = RuntimeException().toSingle<Int>()
        try {
            errorSingle.subscribeBy {
                onSuccess { events.add("onSuccess($it)") }
            }
        } catch (e: Throwable) {
            val name = e.cause?.javaClass?.simpleName
            events.add("catch($name)")
        }
        
        assertEquals(listOf("catch(OnErrorNotImplementedException)"), events)
    }
}
