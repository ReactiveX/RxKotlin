package rx.lang.kotlin

import io.reactivex.CompletableObserver
import io.reactivex.MaybeObserver
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class FunctionSubscriber<T : Any> : Observer<T>, MaybeObserver<T>, SingleObserver<T>, CompletableObserver {
    
    private val onCompleteFunctions = ArrayList<() -> Unit>()
    private val onErrorFunctions = ArrayList<(e: Throwable) -> Unit>()
    private val onNextFunctions = ArrayList<(value: T) -> Unit>()
    private val onStartFunctions = ArrayList<() -> Unit>()
    var origin: Disposable? = null
    
    override fun onComplete() = onCompleteFunctions.forEach { it() }
    
    override fun onError(e: Throwable?) = (e ?: RuntimeException("exception is unknown")).let { ex ->
        if (onErrorFunctions.isEmpty()) {
            throw OnErrorNotImplementedException(ex)
        } else {
            onErrorFunctions.forEach { it(ex) }
        }
    }
    
    override fun onNext(value: T) = onNextFunctions.forEach { it(value) }
    override fun onSuccess(value: T) = onNext(value)
    
    override fun onSubscribe(d: Disposable?) {
        origin = d
        onStartFunctions.forEach { it() }
    }
    
    fun onCompleted(onCompletedFunction: () -> Unit): FunctionSubscriber<T> = copy { onCompleteFunctions.add(onCompletedFunction) }
    fun onError(onErrorFunction: (t: Throwable) -> Unit): FunctionSubscriber<T> = copy { onErrorFunctions.add(onErrorFunction) }
    fun onNext(onNextFunction: (t: T) -> Unit): FunctionSubscriber<T> = copy { onNextFunctions.add(onNextFunction) }
    fun onStart(onStartFunction: () -> Unit): FunctionSubscriber<T> = copy { onStartFunctions.add(onStartFunction) }
    
    private fun copy(block: FunctionSubscriber<T>.() -> Unit): FunctionSubscriber<T> {
        val newSubscriber = FunctionSubscriber<T>()
        newSubscriber.onCompleteFunctions.addAll(onCompleteFunctions)
        newSubscriber.onErrorFunctions.addAll(onErrorFunctions)
        newSubscriber.onNextFunctions.addAll(onNextFunctions)
        newSubscriber.onStartFunctions.addAll(onStartFunctions)
        
        newSubscriber.block()
        
        return newSubscriber
    }
}

class OnErrorNotImplementedException(ex: Throwable) : RuntimeException(ex)

class FunctionSubscriberModifier<T : Any>(init: FunctionSubscriber<T> = subscriber()) {
    var subscriber: FunctionSubscriber<T> = init
        private set
    
    fun onComplete(onCompletedFunction: () -> Unit): Unit {
        subscriber = subscriber.onCompleted(onCompletedFunction)
    }
    
    fun onError(onErrorFunction: (t: Throwable) -> Unit): Unit {
        subscriber = subscriber.onError(onErrorFunction)
    }
    
    fun onNext(onNextFunction: (t: T) -> Unit): Unit {
        subscriber = subscriber.onNext(onNextFunction)
    }
    
    fun onStart(onStartFunction: () -> Unit): Unit {
        subscriber = subscriber.onStart(onStartFunction)
    }
    
    fun onSuccess(onSuccessFunction: (t: T) -> Unit): Unit {
        subscriber = subscriber.onNext(onSuccessFunction)
    }
}

fun <T : Any> subscriber(): FunctionSubscriber<T> = FunctionSubscriber()