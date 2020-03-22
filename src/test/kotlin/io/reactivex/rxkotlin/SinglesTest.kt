package io.reactivex.rxkotlin

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.functions.Function4
import io.reactivex.functions.Function5
import io.reactivex.functions.Function6
import io.reactivex.functions.Function7
import io.reactivex.functions.Function8
import io.reactivex.functions.Function9
import org.junit.Assert.assertEquals
import org.junit.Test

class SinglesTest : KotlinTests() {

    @Test fun zipTwoSinglesWithExplicitZipper() {
        val first = Single.just(1)
        val second = Single.just(2)

        val expected = Single.zip(first, second, BiFunction<Int, Int, Pair<Int, Int>> { f, s -> Pair(s, f)}).blockingGet()
        val actual = Singles.zip(first, second) { f, s -> Pair(s, f) }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipTwoSinglesWithNoExplicitZipper() {
        val first = Single.just(1)
        val second = Single.just(2)

        val expected = Single.zip(first, second, BiFunction<Int, Int, Pair<Int, Int>> { f, s -> Pair(f, s)}).blockingGet()
        val actual = Singles.zip(first, second).blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipThreeSinglesWithExplicitZipper() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)

        val expected = Single.zip(first, second, third, Function3<Int, Int, Int, Triple<Int, Int, Int>> { f, s, t -> Triple(t, s, f)}).blockingGet()
        val actual = Singles.zip(first, second, third) { f, s, t -> Triple(t, s, f) }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipThreeSinglesWithNoExplicitZipper() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)

        val expected = Single.zip(first, second, third, Function3<Int, Int, Int, Triple<Int, Int, Int>> { f, s, t -> Triple(f, s, t)}).blockingGet()
        val actual = Singles.zip(first, second, third).blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipFourSingles() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)
        val fourth = Single.just(4)

        val zipperFunction = Function4<Int, Int, Int, Int, String> { t1, t2, t3, t4 ->
            "$t1$t2$t3$t4"
        }

        val expected = Single.zip(first, second, third, fourth, zipperFunction).blockingGet()
        val actual = Singles.zip(first, second, third, fourth) { t1, t2, t3, t4 ->
            "$t1$t2$t3$t4"
        }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipFiveSingles() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)
        val fourth = Single.just(2)
        val fifth = Single.just(3)

        val zipperFunction = Function5<Int, Int, Int, Int, Int, String> { t1, t2, t3, t4, t5 ->
            "$t1$t2$t3$t4$t5"
        }

        val expected = Single.zip(first, second, third, fourth, fifth, zipperFunction).blockingGet()
        val actual = Singles.zip(first, second, third, fourth, fifth) { t1, t2, t3, t4, t5 ->
            "$t1$t2$t3$t4$t5"
        }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipSixSingles() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)
        val fourth = Single.just(4)
        val fifth = Single.just(5)
        val sixth = Single.just(6)

        val zipperFunction = Function6<Int, Int, Int, Int, Int, Int, String> { t1, t2, t3, t4, t5, t6 ->
            "$t1$t2$t3$t4$t5$t6"
        }

        val expected = Single.zip(first, second, third, fourth, fifth, sixth, zipperFunction).blockingGet()
        val actual = Singles.zip(first, second, third, fourth, fifth, sixth) { t1, t2, t3, t4, t5, t6 ->
            "$t1$t2$t3$t4$t5$t6"
        }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipSevenSingles() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)
        val fourth = Single.just(4)
        val fifth = Single.just(5)
        val sixth = Single.just(6)
        val seventh = Single.just(7)

        val zipperFunction = Function7<Int, Int, Int, Int, Int, Int, Int, String> { t1, t2, t3, t4, t5, t6, t7 ->
            "$t1$t2$t3$t4$t5$t6$t7"
        }

        val expected = Single.zip(first, second, third, fourth, fifth, sixth, seventh, zipperFunction).blockingGet()
        val actual = Singles.zip(first, second, third, fourth, fifth, sixth, seventh) { t1, t2, t3, t4, t5, t6, t7 ->
            "$t1$t2$t3$t4$t5$t6$t7"
        }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipEightSingles() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)
        val fourth = Single.just(4)
        val fifth = Single.just(5)
        val sixth = Single.just(6)
        val seventh = Single.just(7)
        val eighth = Single.just(8)

        val zipperFunction = Function8<Int, Int, Int, Int, Int, Int, Int, Int, String> { t1, t2, t3, t4, t5, t6, t7, t8 ->
            "$t1$t2$t3$t4$t5$t6$t7$t8"
        }

        val expected = Single.zip(first, second, third, fourth, fifth, sixth, seventh, eighth, zipperFunction).blockingGet()
        val actual = Singles.zip(first, second, third, fourth, fifth, sixth, seventh, eighth) { t1, t2, t3, t4, t5, t6, t7, t8 ->
            "$t1$t2$t3$t4$t5$t6$t7$t8"
        }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipNineSingles() {
        val first = Single.just(1)
        val second = Single.just(2)
        val third = Single.just(3)
        val fourth = Single.just(4)
        val fifth = Single.just(5)
        val sixth = Single.just(6)
        val seventh = Single.just(7)
        val eighth = Single.just(8)
        val ninth = Single.just(9)

        val zipperFunction = Function9<Int, Int, Int, Int, Int, Int, Int, Int, Int, String> { t1, t2, t3, t4, t5, t6, t7, t8, t9 ->
            "$t1$t2$t3$t4$t5$t6$t7$t8$t9"
        }

        val expected = Single.zip(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, zipperFunction).blockingGet()
        val actual = Singles.zip(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth) { t1, t2, t3, t4, t5, t6, t7, t8, t9 ->
            "$t1$t2$t3$t4$t5$t6$t7$t8$t9"
        }.blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipWith() {
        val first = Single.just(1)
        val second = Single.just(2)

        val expected = first.zipWith(second, BiFunction<Int, Int, Pair<Int, Int>> { f, s -> Pair(f, s)}).blockingGet()
        val actual = first.zipWith(second).blockingGet()

        assertEquals(expected, actual)
    }

    @Test fun zipWithExplicitZipper() {
        val first = Single.just(1)
        val second = Single.just(2)

        val expected = first.zipWith(second, BiFunction<Int, Int, Pair<Int, Int>> { f, s -> Pair(s, f)}).blockingGet()
        val actual = first.zipWith(second) { f, s -> Pair(s, f) }.blockingGet()

        assertEquals(expected, actual)
    }
}
