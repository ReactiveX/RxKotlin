package rx.lang.kotlin

import rx.SingleSubscriber
import rx.Subscriber
import rx.exceptions.OnErrorNotImplementedException
import rx.observers.SerializedSubscriber
import rx.subscriptions.Subscriptions
import java.util.*

class FunctionSubscriber<T>() : Subscriber<T>() {
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

class FunctionSingleSubscriber<T>() : SingleSubscriber<T>() {
    private val onSuccessFunctions = ArrayList<(value: T) -> Unit>()
    private val onErrorFunctions = ArrayList<(e: Throwable) -> Unit>()

    override fun onSuccess(t: T) = onSuccessFunctions.forEach { it(t) }

    override fun onError(e: Throwable?) = (e ?: RuntimeException("exception is unknown")).let { ex ->
        if (onErrorFunctions.isEmpty()) {
            throw OnErrorNotImplementedException(ex)
        } else {
            onErrorFunctions.forEach { it(ex) }
        }
    }

    fun onSuccess(onSuccessFunction: (t: T) -> Unit): FunctionSingleSubscriber<T> = copy { onSuccessFunctions.add(onSuccessFunction) }
    fun onError(onErrorFunction: (e: Throwable) -> Unit): FunctionSingleSubscriber<T> = copy { onErrorFunctions.add(onErrorFunction) }

    private fun copy(block: FunctionSingleSubscriber<T>.() -> Unit): FunctionSingleSubscriber<T> {
        val newSubscriber = FunctionSingleSubscriber<T>()
        newSubscriber.onSuccessFunctions.addAll(onSuccessFunctions)
        newSubscriber.onErrorFunctions.addAll(onErrorFunctions)

        newSubscriber.block()

        return newSubscriber
    }
}

class FunctionSubscriberModifier<T>(init: FunctionSubscriber<T> = subscriber()) {
    var subscriber: FunctionSubscriber<T> = init
        private set

    fun onCompleted(onCompletedFunction: () -> Unit) : Unit { subscriber = subscriber.onCompleted(onCompletedFunction) }
    fun onError(onErrorFunction: (t : Throwable) -> Unit) : Unit { subscriber = subscriber.onError(onErrorFunction) }
    fun onNext(onNextFunction: (t : T) -> Unit) : Unit { subscriber = subscriber.onNext(onNextFunction) }
    fun onStart(onStartFunction : () -> Unit) : Unit { subscriber = subscriber.onStart(onStartFunction) }
}

class FunctionSingleSubscriberModifier<T>(init: FunctionSingleSubscriber<T> = singleSubscriber()) {
    var subscriber: FunctionSingleSubscriber<T> = init
        private set

    fun onSuccess(onSuccessFunction: (t: T) -> Unit): Unit { subscriber = subscriber.onSuccess(onSuccessFunction) }
    fun onError(onErrorFunction: (r: Throwable) -> Unit): Unit {subscriber = subscriber.onError(onErrorFunction) }
}

fun <T> subscriber(): FunctionSubscriber<T> = FunctionSubscriber()
fun <T> singleSubscriber(): FunctionSingleSubscriber<T> = FunctionSingleSubscriber()
fun <T> Subscriber<T>.synchronized(): Subscriber<T> = SerializedSubscriber(this)
fun Subscriber<*>.add(action: () -> Unit) = add(Subscriptions.create(action))
