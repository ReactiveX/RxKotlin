package rx.lang.kotlin.examples

import rx.Observable
import rx.lang.kotlin.observable
import rx.lang.kotlin.onError
import rx.lang.kotlin.plusAssign
import rx.lang.kotlin.toObservable
import rx.lang.kotlin.zip
import rx.lang.kotlin.combineLatest
import rx.subscriptions.CompositeSubscription
import java.net.URL
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val subscrition = CompositeSubscription()

    val printArticle = { art: String ->
        println("--- Article ---\n${art.substring(0, 125)}")
    }

    val printIt = { it: String -> println(it) }

    subscrition += asyncObservable().subscribe(printIt)

    subscrition += syncObservable().subscribe(printIt)

    subscrition.clear()

    simpleComposition()

    asyncWiki("Tiger", "Elephant").subscribe(printArticle)

    asyncWikiWithErrorHandling("Tiger", "Elephant").subscribe(printArticle) { e ->
        println("--- Error ---\n${e.message}")
    }

    combineLatest(listOfObservables())

    zip(listOfObservables())
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

public fun listOfObservables(): List<Observable<String>> = listOf(syncObservable(), syncObservable())

public fun combineLatest(observables: List<Observable<String>>) {
    observables.combineLatest { it.reduce { one, two -> one + two } }.subscribe { println(it) }
}

public fun zip(observables: List<Observable<String>>) {
    observables.zip { it.reduce { one, two -> one + two } }.subscribe { println(it) }
}