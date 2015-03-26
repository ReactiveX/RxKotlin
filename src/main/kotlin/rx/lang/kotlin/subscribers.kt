package rx.lang.kotlin

import rx.Subscriber
import rx.observers.SerializedSubscriber
import rx.exceptions.OnErrorNotImplementedException

public class FunctionSubscriber<T>(onCompletedFunction: () -> Unit, onErrorFunction: (e : Throwable) -> Unit, onNextFunction: (value : T) -> Unit, onStartFunction : () -> Unit) : Subscriber<T>() {
    private val onCompletedFunction: () -> Unit = onCompletedFunction
    private val onErrorFunction: (e : Throwable) -> Unit = onErrorFunction
    private val onNextFunction: (value : T) -> Unit = onNextFunction
    private val onStartFunction : () -> Unit = onStartFunction

    override fun onCompleted() = onCompletedFunction()

    override fun onError(e: Throwable?) = onErrorFunction(e ?: RuntimeException("exception is unknown"))

    override fun onNext(t: T) = onNextFunction(t)

    override fun onStart() = onStartFunction()

    fun onCompleted(onCompletedFunction: () -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(onCompletedFunction, this.onErrorFunction, this.onNextFunction, this.onStartFunction)
    fun onError(onErrorFunction: (t : Throwable) -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(this.onCompletedFunction, onErrorFunction, this.onNextFunction, this.onStartFunction)
    fun onNext(onNextFunction: (t : T) -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(this.onCompletedFunction, this.onErrorFunction, onNextFunction, this.onStartFunction)
    fun onStart(onStartFunction : () -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(this.onCompletedFunction, this.onErrorFunction, this.onNextFunction, onStartFunction)
}

public class FunctionSubscriberModifier<T>(init: FunctionSubscriber<T> = subscriber()) {
    public var subscriber: FunctionSubscriber<T> = init
        private set

    fun onCompleted(onCompletedFunction: () -> Unit) : Unit { subscriber = subscriber.onCompleted(onCompletedFunction) }
    fun onError(onErrorFunction: (t : Throwable) -> Unit) : Unit { subscriber = subscriber.onError(onErrorFunction) }
    fun onNext(onNextFunction: (t : T) -> Unit) : Unit { subscriber = subscriber.onNext(onNextFunction) }
    fun onStart(onStartFunction : () -> Unit) : Unit { subscriber = subscriber.onStart(onStartFunction) }
}

public fun <T> subscriber(): FunctionSubscriber<T> = FunctionSubscriber({}, {throw OnErrorNotImplementedException(it)}, {}, {})
public fun <T> Subscriber<T>.synchronized() : Subscriber<T>  = SerializedSubscriber(this)
