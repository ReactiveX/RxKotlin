package io.reactivex.rxjava3.kotlin

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
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
}

fun SingleSourceInt(i: Int): SingleSource<Int> {
    return Single.create({ s -> s.onSuccess(i)})
}