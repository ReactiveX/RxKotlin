package io.reactivex.rxkotlin

import io.reactivex.Flowable
import org.junit.Test

class FlowablesTest : KotlinTests() {

    @Test fun zipFlowablesWithEmptyListReturnsEmptyList() {
        val flowables = emptyList<Flowable<Int>>()

        val zippedFlowables = flowables.zipFlowables().blockingFirst()

        assert(zippedFlowables.isEmpty())
    }

    @Test fun zipObservablesWithNonEmptyListReturnsNonEmptyListWithCorrectElements() {
        val flowables = listOf(
            Flowable.just(1),
            Flowable.just(2),
            Flowable.just(3)
        )

        val zippedFlowables = flowables.zipFlowables().blockingFirst()

        assert(zippedFlowables.size == 3)
        assert(zippedFlowables[0] == 1)
        assert(zippedFlowables[1] == 2)
        assert(zippedFlowables[2] == 3)
    }
}