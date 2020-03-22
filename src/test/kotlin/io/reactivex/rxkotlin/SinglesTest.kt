package io.reactivex.rxkotlin

import io.reactivex.Single
import io.reactivex.SingleSource
import org.junit.Test

class SinglesTest : KotlinTests() {

    @Test fun testParameterOrder() {
        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                {one, two ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3),
                {one, two, three ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3), SingleSourceInt(4),
                {one, two, three, four ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                    assert(four == 4, { -> "Should equal four"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3), SingleSourceInt(4),
                SingleSourceInt(5),
                {one, two, three, four, five ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                    assert(four == 4, { -> "Should equal four"})
                    assert(five == 5, { -> "Should equal five"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3), SingleSourceInt(4),
                SingleSourceInt(5), SingleSourceInt(6),
                {one, two, three, four, five, six ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                    assert(four == 4, { -> "Should equal four"})
                    assert(five == 5, { -> "Should equal five"})
                    assert(six == 6, { -> "Should equal six"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3), SingleSourceInt(4),
                SingleSourceInt(5), SingleSourceInt(6),
                SingleSourceInt(7),
                {one, two, three, four, five, six, seven ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                    assert(four == 4, { -> "Should equal four"})
                    assert(five == 5, { -> "Should equal five"})
                    assert(six == 6, { -> "Should equal six"})
                    assert(seven == 7, { -> "Should equal seven"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3), SingleSourceInt(4),
                SingleSourceInt(5), SingleSourceInt(6),
                SingleSourceInt(7), SingleSourceInt(8),
                {one, two, three, four, five, six, seven, eight ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                    assert(four == 4, { -> "Should equal four"})
                    assert(five == 5, { -> "Should equal five"})
                    assert(six == 6, { -> "Should equal six"})
                    assert(seven == 7, { -> "Should equal seven"})
                    assert(eight == 8, { -> "Should equal eight"})
                }).blockingGet()

        Singles.zip(
                SingleSourceInt(1), SingleSourceInt(2),
                SingleSourceInt(3), SingleSourceInt(4),
                SingleSourceInt(5), SingleSourceInt(6),
                SingleSourceInt(7), SingleSourceInt(8),
                SingleSourceInt(9),
                {one, two, three, four, five, six, seven, eight, nine ->
                    assert(one == 1, { -> "Should equal one"})
                    assert(two == 2, { -> "Should equal two"})
                    assert(three == 3, { -> "Should equal three"})
                    assert(four == 4, { -> "Should equal four"})
                    assert(five == 5, { -> "Should equal five"})
                    assert(six == 6, { -> "Should equal six"})
                    assert(seven == 7, { -> "Should equal seven"})
                    assert(eight == 8, { -> "Should equal eight"})
                    assert(nine == 9, { -> "Should equal nine"})
                }).blockingGet()
    }

    @Test fun zipSinglesWithEmptyListReturnsEmptyList() {
        val singles = emptyList<Single<Int>>()

        val zippedSingles = singles.zipSingles().blockingGet()

        assert(zippedSingles.isEmpty())
    }

    @Test fun zipSinglesWithNonEmptyListReturnsNonEmptyListWithCorrectElements() {
        val singles = listOf(
            Single.just(1),
            Single.just(2),
            Single.just(3)
        )

        val zippedSingles = singles.zipSingles().blockingGet()

        assert(zippedSingles.size == 3)
        assert(zippedSingles[0] == 1)
        assert(zippedSingles[1] == 2)
        assert(zippedSingles[2] == 3)
    }
}

fun SingleSourceInt(i: Int): SingleSource<Int> {
    return Single.create({ s -> s.onSuccess(i)})
}