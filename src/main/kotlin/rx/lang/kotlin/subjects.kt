package rx.lang.kotlin

import rx.schedulers.TestScheduler
import rx.subjects.*

fun <T> BehaviourSubject() : BehaviorSubject<T> = BehaviorSubject.create()
fun <T> BehaviourSubject(default : T) : BehaviorSubject<T> = BehaviorSubject.create(default)
fun <T> AsyncSubject() : AsyncSubject<T> = AsyncSubject.create()
fun <T> PublishSubject() : PublishSubject<T> = PublishSubject.create()
fun <T> ReplaySubject(capacity : Int = 16) : ReplaySubject<T> = ReplaySubject.create(capacity)

fun <F, T> Subject<F, T>.synchronized() : Subject<F, T> = SerializedSubject(this)
fun <T> TestSubject(scheduler: TestScheduler) : TestSubject<T> = TestSubject.create(scheduler)
