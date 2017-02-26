package rx.lang.kotlin.examples

import rx.Observable
import rx.lang.kotlin.addTo
import rx.lang.kotlin.combineLatest
import rx.lang.kotlin.onError
import rx.lang.kotlin.plusAssign
import rx.lang.kotlin.subscribeBy
import rx.lang.kotlin.toObservable
import rx.lang.kotlin.zip
import rx.subscriptions.CompositeSubscription
import java.net.URL
import java.util.Scanner
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

    simpleObservable().subscribeBy(
            onNext = { s: String -> println("1st onNext => $s") } andThen { s -> println("2nd onNext => $s") }
    )

    addToCompositeSubscription()
}

private fun URL.toScannerObservable() = Observable.create<String> { s ->
    this.openStream().use { stream ->
        Scanner(stream)
                .useDelimiter("\\A")
                .toObservable()
                .subscribe { s.onNext(it) }
    }
}

fun syncObservable(): Observable<String> =
        Observable.create { subscriber ->
            (0..75).toObservable()
                    .map { "Sync value_$it" }
                    .subscribe(subscriber)
        }

fun asyncObservable(): Observable<String> =
        Observable.create { subscriber ->
            thread {
                (0..75).toObservable()
                        .map { "Async value_$it" }
                        .subscribe(subscriber)
            }
        }

fun asyncWiki(vararg articleNames: String): Observable<String> =
        Observable.create { subscriber ->
            thread {
                articleNames.toObservable()
                        .flatMap { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().first() }
                        .subscribe(subscriber)
            }
        }

fun asyncWikiWithErrorHandling(vararg articleNames: String): Observable<String> =
        Observable.create { subscriber ->
            thread {
                articleNames.toObservable()
                        .flatMap { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().first() }
                        .onError { e ->
                            subscriber.onError(e)
                        }
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
    observables.zip { it.reduce { one, two -> one + two } }.subscribe(::println)
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

infix inline fun <T : Any> ((T) -> Unit).andThen(crossinline block: (T) -> Unit): (T) -> Unit = { this(it); block(it) }
