package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.annotations.BackpressureKind
import io.reactivex.rxjava3.annotations.BackpressureSupport
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.SchedulerSupport
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.internal.functions.Functions


private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

private fun <T : Any> ((T) -> Unit).asConsumer(): Consumer<T> {
    return if (this === onNextStub) Functions.emptyConsumer() else Consumer(this)
}

private fun ((Throwable) -> Unit).asOnErrorConsumer(): Consumer<Throwable> {
    return if (this === onErrorStub) Functions.ON_ERROR_MISSING else Consumer(this)
}

private fun (() -> Unit).asOnCompleteAction(): Action {
    return if (this === onCompleteStub) Functions.EMPTY_ACTION else Action(this)
}

/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Disposable = subscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Disposable = subscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onSuccess: (T) -> Unit = onNextStub
): Disposable = subscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Maybe<T>.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onSuccess: (T) -> Unit = onNextStub
): Disposable = subscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded subscribe function that allows passing named parameters
 */
@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun Completable.subscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = when {
    // There are optimized versions of the completable Consumers, so we need to use the subscribe overloads
    // here.
    onError === onErrorStub && onComplete === onCompleteStub -> subscribe()
    onError === onErrorStub -> subscribe(onComplete)
    else -> subscribe(onComplete.asOnCompleteAction(), Consumer(onError))
}

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<T>.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Unit = blockingSubscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<T>.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onNext: (T) -> Unit = onNextStub
): Unit = blockingSubscribe(onNext.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Maybe<T>.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub,
        onSuccess: (T) -> Unit = onNextStub
) : Unit = blockingSubscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer(), onComplete.asOnCompleteAction())

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onSuccess: (T) -> Unit = onNextStub
) : Unit = blockingSubscribe(onSuccess.asConsumer(), onError.asOnErrorConsumer())

/**
 * Overloaded blockingSubscribe function that allows passing named parameters
 */
@SchedulerSupport(SchedulerSupport.NONE)
fun Completable.blockingSubscribeBy(
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Unit = blockingSubscribe(onComplete.asOnCompleteAction(), onError.asOnErrorConsumer())
