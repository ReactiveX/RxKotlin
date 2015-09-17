/**
 * Copyright 2013 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rx.lang.kotlin

import rx.Observable
import rx.Subscriber

@Deprecated("use observable {} instead")
public fun<T> Function1<Subscriber<in T>, Unit>.asObservable(): Observable<T> {
    return Observable.create(this)
}

@Deprecated("use deferredObservable {} instead")
public fun<T> Function0<Observable<T>>.defer(): Observable<T> {
    return Observable.defer(this)
}

@Deprecated("use toObservable() instead")
fun IntRange.asObservable(): Observable<Int> {
    return Observable.range(start, end)
}

@Deprecated("use toObservable() instead")
public fun<T> Iterable<T>.asObservable(): Observable<T> {
    return Observable.from(this)
}

@Deprecated("use toSingletonObservable() instead")
public fun<T> T.asObservable(): Observable<T> {
    return Observable.just(this)
}

@Deprecated("use toObservable() instead")
public fun<T> Throwable.asObservable(): Observable<T> {
    return Observable.error(this)
}

@Deprecated("use listOf().toObservable() instead")
public fun<T> Pair<T, T>.asObservable(): Observable<T> {
    return Observable.from(listOf(first, second))
}

@Deprecated("use listOf().toObservable() instead")
public fun<T> Triple<T, T, T>.asObservable(): Observable<T> {
    return Observable.from(listOf(first, second, third))
}

@Deprecated("use first.mergeWith(second) or listOf(first, second).merge() instead")
public fun<T> Pair<Observable<T>, Observable<T>>.merge(): Observable<T> {
    return Observable.merge(first, second)
}

@Deprecated("use listOf(first, second, third).merge() instead")
public fun<T> Triple<Observable<T>, Observable<T>, Observable<T>>.merge(): Observable<T> {
    return Observable.merge(first, second, third)
}

@Deprecated("use listOf(first, second).mergeDelayError() instead")
public fun<T> Pair<Observable<T>, Observable<T>>.mergeDelayError(): Observable<T> {
    return Observable.mergeDelayError(first, second)
}

@Deprecated("use listOf(first, second, third).mergeDelayError() instead")
public fun<T> Triple<Observable<T>, Observable<T>, Observable<T>>.mergeDelayError(): Observable<T> {
    return Observable.mergeDelayError(first, second, third)
}
