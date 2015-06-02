package rx.lang.kotlin.examples

import rx.Observable
import kotlin.concurrent.thread
import java.util.Scanner
import java.net.URL
import rx.lang.kotlin.observable
import rx.lang.kotlin.toObservable
import rx.lang.kotlin.onError

fun main(args: Array<String>) {

    val printArticle = { art: String ->
        println("--- Article ---\n${art.substring(0, 125)}")
    }

    val printIt = { it: String -> println(it) }

    asyncObservable().subscribe(printIt)

    syncObservable().subscribe(printIt)

    simpleComposition()

    asyncWiki("Tiger", "Elephant").subscribe(printArticle)

    asyncWikiWithErrorHandling("Tiger", "Elephant").subscribe(printArticle) { e ->
        println("--- Error ---\n${e.getMessage()}")
    }

}

private fun URL.toScannerObservable() = observable<String> { s ->
    this.openStream().use { stream ->
        Scanner(stream).useDelimiter("\\A").toObservable().subscribe(s)
    }
}

public fun syncObservable(): Observable<String> =
    observable { subscriber ->
        (0..75).toObservable()
                .map { "Sync value_$it" }
                .subscribe(subscriber)
    }

public fun asyncObservable(): Observable<String> =
    observable { subscriber ->
        thread {
            (0..75).toObservable()
                    .map { "Async value_$it" }
                    .subscribe(subscriber)
        }
    }

public fun asyncWiki(vararg articleNames: String): Observable<String> =
    observable { subscriber ->
        thread {
            articleNames.toObservable()
                    .flatMap { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().first() }
                    .subscribe(subscriber)
        }
    }

public fun asyncWikiWithErrorHandling(vararg articleNames: String): Observable<String> =
    observable { subscriber ->
        thread {
            articleNames.toObservable()
                    .flatMap { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().first() }
                    .onError { e ->
                        subscriber.onError(e) }
                    .subscribe(subscriber)
        }
    }

public fun simpleComposition() {
    asyncObservable().skip(10).take(5)
            .map { s -> "${s}_xform" }
            .subscribe { s -> println("onNext => $s") }
}