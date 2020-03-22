package io.reactivex.rxkotlin

import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test


class ObservablesTest {
    @Test fun testCombineLatestEmitPair() {
        val pair = Pair(1, "a")
        val result = Observables.combineLatest(
            Observable.just(pair.first),
            Observable.just(pair.second)
        ).blockingFirst()

        assertEquals(pair, result)
    }

    @Test fun testCombineLatestEmitTriple() {
        val triple = Triple(1, "a", 1.0)
        val result = Observables.combineLatest(
            Observable.just(triple.first),
            Observable.just(triple.second),
            Observable.just(triple.third)
        ).blockingFirst()

        assertEquals(triple, result)
    }

    @Test fun testWithLatestFromEmitPair() {
        val pair = Pair(1, "a")
        val result = Observable.just(pair.first)
                        .withLatestFrom(Observable.just(pair.second))
                        .blockingFirst()

        assertEquals(pair, result)
    }

    @Test fun testWithLatestFromEmitTriple(){
        val triple = Triple(1, "a", 1.0)
        val result = Observable.just(triple.first)
                        .withLatestFrom(Observable.just(triple.second), Observable.just(triple.third))
                        .blockingFirst()

        assertEquals(triple, result)
    }

    @Test fun zipObservablesWithEmptyListReturnsEmptyList() {
        val observables = emptyList<Observable<Int>>()

        val zippedObservables = observables.zipObservables().blockingFirst()

        assert(zippedObservables.isEmpty())
    }

    @Test fun zipObservablesWithNonEmptyListReturnsNonEmptyListWithCorrectElements() {
        val observables = listOf(
            Observable.just(1),
            Observable.just(2),
            Observable.just(3)
        )

        val zippedObservables = observables.zipObservables().blockingFirst()

        assert(zippedObservables.size == 3)
        assert(zippedObservables[0] == 1)
        assert(zippedObservables[1] == 2)
        assert(zippedObservables[2] == 3)
    }
}