package rx.lang.kotlin

import org.junit.Test
import rx.Observable
import java.util.HashSet

class OperatorsTest {

    @Test
    fun collectDSL() {
        val source = Observable.just("Alpha","Beta","Gamma","Delta")

        source.collect<String,HashSet<String>> {
            stateFactory { HashSet() }
            collector { str, set -> set += str  }
        }.subscribe { println(it) }
    }
}