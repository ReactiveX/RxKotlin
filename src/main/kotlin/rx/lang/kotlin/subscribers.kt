package rx.lang.kotlin

import rx.Subscriber
import rx.observers.SerializedSubscriber

public class FunctionSubscriber<T>(onCompletedFunction: () -> Unit, onErrorFunction: (e : Throwable) -> Unit, onNextFunction: (value : T) -> Unit) : Subscriber<T>() {
    private val onCompletedFunction: () -> Unit = onCompletedFunction
    private val onErrorFunction: (e : Throwable) -> Unit = onErrorFunction
    private val onNextFunction: (value : T) -> Unit = onNextFunction

    override fun onCompleted() = onCompletedFunction()

    override fun onError(e: Throwable?) = onErrorFunction(e ?: RuntimeException("exception is unknown"))

    override fun onNext(t: T) = onNextFunction(t)

    fun onCompleted(onCompletedFunction: () -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(onCompletedFunction, this.onErrorFunction, this.onNextFunction)
    fun onError(onErrorFunction: (t : Throwable) -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(this.onCompletedFunction, onErrorFunction, this.onNextFunction)
    fun onNext(onNextFunction: (t : T) -> Unit) : FunctionSubscriber<T> = FunctionSubscriber(this.onCompletedFunction, this.onErrorFunction, onNextFunction)
}

public fun <T> subscriber(): FunctionSubscriber<T> = FunctionSubscriber({}, {}, {})
public fun <T> Subscriber<T>.synchronized() : Subscriber<T>  = SerializedSubscriber(this)
