package rx.lang.kotlin.examples

import rx.Observable
import rx.lang.kotlin.*
import rx.subscriptions.CompositeSubscription
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val subscription = CompositeSubscription()

    val printArticle = { art: String ->
        println("--- Article ---\n${art.substring(0, 125)}")
    }

    val printIt = { it: String -> println(it) }

    subscription += asyncObservable().subscribe(printIt)

    subscription += syncObservable().subscribe(printIt)

    subscription.clear()

    simpleComposition()

    asyncWiki("Tiger", "Elephant").subscribe(printArticle)

    asyncWikiWithErrorHandling("Tiger", "Elephant").subscribe(printArticle) { e ->
        println("--- Error ---\n${e.message}")
    }

    combineLatest(listOfObservables())

    zip(listOfObservables())

    simpleObservable().subscribe(FunctionSubscriber<String>()
            .onNext { s -> println("1st onNext => $s") }
            .onNext { s -> println("2nd onNext => $s") })

    addToCompositeSubscription()
}

private fun URL.toScannerObservable() = observable<String> { s ->
    this.openStream().use { stream ->
        Scanner(stream).useDelimiter("\\A").toObservable().subscribe(s)
    }
}

fun syncObservable(): Observable<String> =
    observable { subscriber ->
        (0..75).toObservable()
                .map { "Sync value_$it" }
                .subscribe(subscriber)
    }

fun asyncObservable(): Observable<String> =
    observable { subscriber ->
        thread {
            (0..75).toObservable()
                    .map { "Async value_$it" }
                    .subscribe(subscriber)
        }
    }

fun asyncWiki(vararg articleNames: String): Observable<String> =
    observable { subscriber ->
        thread {
            articleNames.toObservable()
                    .flatMap { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().first() }
                    .subscribe(subscriber)
        }
    }

fun asyncWikiWithErrorHandling(vararg articleNames: String): Observable<String> =
    observable { subscriber ->
        thread {
            articleNames.toObservable()
                    .flatMap { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().first() }
                    .onError { e ->
                        subscriber.onError(e) }
                    .subscribe(subscriber)
        }
    }

fun simpleComposition() {
    asyncObservable().skip(10).take(5)
            .map { s -> "${s}_xform" }
            .subscribe { s -> println("onNext => $s") }
}

fun listOfObservables(): List<Observable<String>> = listOf(syncObservable(), syncObservable())

fun combineLatest(observables: List<Observable<String>>) {
    observables.combineLatest { it.reduce { one, two -> one + two } }.subscribe { println(it) }
}

fun zip(observables: List<Observable<String>>) {
    observables.zip { it.reduce { one, two -> one + two } }.subscribe { println(it) }
}

fun simpleObservable(): Observable<String> = (0..17).toObservable().map { "Simple $it" }

fun addToCompositeSubscription() {
    val compositeSubscription = CompositeSubscription()

    Observable.just("test")
            .delay(100, TimeUnit.MILLISECONDS)
            .subscribe()
            .addTo(compositeSubscription)

    compositeSubscription.unsubscribe()
}