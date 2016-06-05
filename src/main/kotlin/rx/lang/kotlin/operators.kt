package rx.lang.kotlin

import rx.Observable

fun <T,R> Observable<T>.collect(op: CollectDSL<T,R>.() -> Unit): Observable<R> {
    val collectDSL = CollectDSL<T,R>()
    op.invoke(collectDSL)
    return collect({collectDSL._stateFactory!!.invoke()}, {r,t -> collectDSL._collector!!.invoke(t,r)})
}

class CollectDSL<T,R> (
    internal var _stateFactory: (() -> R)? = null,
    internal var _collector: ((T,R) -> Unit)? = null
) {
    fun stateFactory(stateFactory: () -> R) {
        _stateFactory = stateFactory
    }
    fun collector(collector: (T,R) -> Unit) {
        _collector = collector
    }
}