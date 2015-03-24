package rx.lang.kotlin

import rx.Subscriber
import rx.exceptions.OnErrorNotImplementedException
import rx.observers.SerializedSubscriber
import java.util.ArrayList

public class FunctionSubscriber<T>() : Subscriber<T>() {
    private val onCompletedFunctions = ArrayList<() -> Unit>()
    private val onErrorFunctions = ArrayList<(e: Throwable) -> Unit>()
    private val onNextFunctions = ArrayList<(value: T) -> Unit>()
    private val onStartFunctions = ArrayList<() -> Unit>()

    override fun onCompleted() = onCompletedFunctions.forEach { it() }

    override fun onError(e: Throwable?) = (e ?: RuntimeException("exception is unknown")).let { ex ->
        if (onErrorFunctions.isEmpty()) {
            throw OnErrorNotImplementedException(ex)
        } else {
            onErrorFunctions.forEach { it(ex) }
        }
    }

    override fun onNext(t: T) = onNextFunctions.forEach { it(t) }

    override fun onStart() = onStartFunctions.forEach { it() }

    fun onCompleted(onCompletedFunction: () -> Unit): FunctionSubscriber<T> = copy { onCompletedFunctions.add(onCompletedFunction) }
    fun onError(onErrorFunction: (t: Throwable) -> Unit): FunctionSubscriber<T> = copy { onErrorFunctions.add(onErrorFunction) }
    fun onNext(onNextFunction: (t: T) -> Unit): FunctionSubscriber<T> = copy { onNextFunctions.add(onNextFunction) }
    fun onStart(onStartFunction : () -> Unit) : FunctionSubscriber<T> = copy { onStartFunctions.add(onStartFunction) }

    private fun copy(block: FunctionSubscriber<T>.() -> Unit): FunctionSubscriber<T> {
        val newSubscriber = FunctionSubscriber<T>()
        newSubscriber.onCompletedFunctions.addAll(onCompletedFunctions)
        newSubscriber.onErrorFunctions.addAll(onErrorFunctions)
        newSubscriber.onNextFunctions.addAll(onNextFunctions)
        newSubscriber.onStartFunctions.addAll(onStartFunctions)

        newSubscriber.block()

        return newSubscriber
    }
}

public fun <T> subscriber(): FunctionSubscriber<T> = FunctionSubscriber()
public fun <T> Subscriber<T>.synchronized(): Subscriber<T> = SerializedSubscriber(this)
