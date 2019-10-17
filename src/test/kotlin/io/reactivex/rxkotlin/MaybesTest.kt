package io.reactivex.rxkotlin

import io.reactivex.Maybe
import org.junit.Test

class MaybesTest : KotlinTests() {

    @Test
    fun zipMaybesWithEmptyListReturnsEmptyList() {
        val maybes = emptyList<Maybe<Int>>()

        val zippedMaybes = maybes.zipMaybes().blockingGet()

        assert(zippedMaybes.isEmpty())
    }

    @Test
    fun zipMaybesWithNonEmptyListReturnsNonEmptyListWithCorrectElements() {
        val maybes = listOf(
            Maybe.just(1),
            Maybe.just(2),
            Maybe.just(3)
        )

        val zippedMaybes = maybes.zipMaybes().blockingGet()

        assert(zippedMaybes.size == 3)
        assert(zippedMaybes[0] == 1)
        assert(zippedMaybes[1] == 2)
        assert(zippedMaybes[2] == 3)
    }
}