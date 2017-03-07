package rx.lang.kotlin

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

fun <T : Any> BehaviorSubject(): BehaviorSubject<T> = BehaviorSubject.create()
fun <T : Any> BehaviorSubject(default: T): BehaviorSubject<T> = BehaviorSubject.createDefault(default)
fun <T : Any> AsyncSubject(): AsyncSubject<T> = AsyncSubject.create()
fun <T : Any> PublishSubject(): PublishSubject<T> = PublishSubject.create()
fun <T : Any> ReplaySubject(capacity: Int = Observable.bufferSize()): ReplaySubject<T> = ReplaySubject.create(capacity)
