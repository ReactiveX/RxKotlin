package rx.lang.kotlin

import rx.schedulers.TestScheduler
import rx.subjects.*

public fun <T> BehaviourSubject() : BehaviorSubject<T> = BehaviorSubject.create()
public fun <T> BehaviourSubject(default : T) : BehaviorSubject<T> = BehaviorSubject.create(default)
public fun <T> AsyncSubject() : AsyncSubject<T> = AsyncSubject.create()
public fun <T> PublishSubject() : PublishSubject<T> = PublishSubject.create()
public fun <T> ReplaySubject(capacity : Int = 16) : ReplaySubject<T> = ReplaySubject.create(capacity)

public fun <F, T> Subject<F, T>.synchronized() : Subject<F, T> = SerializedSubject(this)
public fun <T> TestSubject(scheduler: TestScheduler) : TestSubject<T> = TestSubject.create(scheduler)
