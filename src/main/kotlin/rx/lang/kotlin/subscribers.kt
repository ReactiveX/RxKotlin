package rx.lang.kotlin

import rx.SingleSubscriber
import rx.Subscriber
import rx.exceptions.OnErrorNotImplementedException
import rx.observers.SerializedSubscriber
import rx.subscriptions.Subscriptions
import java.util.*

class FunctionSubscriber<T>(
        private val onCompletedFunctions: List<() -> Unit> = emptyList(),
        private val onErrorFunctions: List<(e: Throwable) -> Unit> = emptyList(),
        private val onNextFunctions: List<(value: T) -> Unit> = emptyList(),
        private val onStartFunctions: List<() -> Unit> = emptyList()
) : Subscriber<T>() {

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

    fun onCompleted(onCompletedFunction: () -> Unit): FunctionSubscriber<T> = copy(
            onCompletedFunctions = onCompletedFunctions + onCompletedFunction
    )

    fun onError(onErrorFunction: (t: Throwable) -> Unit): FunctionSubscriber<T> = copy(
            onErrorFunctions = onErrorFunctions + onErrorFunction
    )

    fun onNext(onNextFunction: (t: T) -> Unit): FunctionSubscriber<T> = copy(
            onNextFunctions = onNextFunctions + onNextFunction
    )

    fun onStart(onStartFunction: () -> Unit): FunctionSubscriber<T> = copy(
            onStartFunctions = onStartFunctions + onStartFunction
    )

    private fun copy(
            onCompletedFunctions: List<() -> Unit> = this.onCompletedFunctions,
            onErrorFunctions: List<(e: Throwable) -> Unit> = this.onErrorFunctions,
            onNextFunctions: List<(value: T) -> Unit> = this.onNextFunctions,
            onStartFunctions: List<() -> Unit> = this.onStartFunctions
    ): FunctionSubscriber<T> = FunctionSubscriber(
            onCompletedFunctions,
            onErrorFunctions,
            onNextFunctions,
            onStartFunctions
    )
}

class FunctionSingleSubscriber<T>(
        private val onSuccessFunctions: List<(value: T) -> Unit> = emptyList(),
        private val onErrorFunctions: List<(e: Throwable) -> Unit> = emptyList()
) : SingleSubscriber<T>() {

    override fun onSuccess(t: T) = onSuccessFunctions.forEach { it(t) }

    override fun onError(e: Throwable?) = (e ?: RuntimeException("exception is unknown")).let { ex ->
        if (onErrorFunctions.isEmpty()) {
            throw OnErrorNotImplementedException(ex)
        } else {
            onErrorFunctions.forEach { it(ex) }
        }
    }

    fun onSuccess(onSuccessFunction: (t: T) -> Unit): FunctionSingleSubscriber<T> = copy(
            onSuccessFunctions = onSuccessFunctions + onSuccessFunction
    )

    fun onError(onErrorFunction: (e: Throwable) -> Unit): FunctionSingleSubscriber<T> = copy(
            onErrorFunctions = onErrorFunctions + onErrorFunction
    )

    private fun copy(
            onSuccessFunctions: List<(value: T) -> Unit> = this.onSuccessFunctions,
            onErrorFunctions: List<(e: Throwable) -> Unit> = this.onErrorFunctions
    ): FunctionSingleSubscriber<T> = FunctionSingleSubscriber(
            onSuccessFunctions,
            onErrorFunctions
    )
}

class FunctionSubscriberModifier<T>(init: FunctionSubscriber<T> = subscriber()) {

    var subscriber: FunctionSubscriber<T> = init
        private set

    fun onCompleted(onCompletedFunction: () -> Unit): Unit {
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
}

class FunctionSingleSubscriberModifier<T>(init: FunctionSingleSubscriber<T> = singleSubscriber()) {

    var subscriber: FunctionSingleSubscriber<T> = init
        private set

    fun onSuccess(onSuccessFunction: (t: T) -> Unit): Unit {
        subscriber = subscriber.onSuccess(onSuccessFunction)
    }

    fun onError(onErrorFunction: (r: Throwable) -> Unit): Unit {
        subscriber = subscriber.onError(onErrorFunction)
    }
}

fun <T> subscriber(): FunctionSubscriber<T> = FunctionSubscriber()
fun <T> singleSubscriber(): FunctionSingleSubscriber<T> = FunctionSingleSubscriber()
fun <T> Subscriber<T>.synchronized(): Subscriber<T> = SerializedSubscriber(this)
fun Subscriber<*>.add(action: () -> Unit) = add(Subscriptions.create(action))
