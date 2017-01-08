package rx.lang.kotlin.examples

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import rx.lang.kotlin.FunctionSubscriber
import rx.lang.kotlin.addTo
import rx.lang.kotlin.combineLatest
import rx.lang.kotlin.observable
import rx.lang.kotlin.plusAssign
import rx.lang.kotlin.subscribeBy
import rx.lang.kotlin.toObservable
import rx.lang.kotlin.zip
import java.net.URL
import java.util.Scanner
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val subscription = CompositeDisposable()

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

private fun URL.toScannerObservable() = observable<String>({ s ->
    this.openStream().use { stream ->
        Scanner(stream).useDelimiter("\\A").toObservable().subscribeBy { s }
    }
})

fun syncObservable(): Observable<String> =
        observable { subscriber ->
            (0..75).toObservable()
                    .map { "Sync value_$it" }
                    .subscribe { subscriber.onNext(it) }
        }

fun asyncObservable(): Observable<String> = observable { subscriber ->
    thread {
        (0..75).toObservable()
                .map { "Async value_$it" }
                .subscribe { subscriber.onNext(it) }
    }
}

fun asyncWiki(vararg articleNames: String): Observable<String> = observable { subscriber ->
    thread {
        articleNames.toObservable()
                .flatMapMaybe { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().firstElement() }
                .subscribe { subscriber.onNext(it) }
    }
}

fun asyncWikiWithErrorHandling(vararg articleNames: String): Observable<String> = observable { subscriber ->
    thread {
        articleNames.toObservable()
                .flatMapMaybe { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().firstElement() }
                .subscribe({ subscriber.onNext(it) }, { subscriber.onError(it) })
    }
}

fun simpleComposition() {
    asyncObservable()
            .skip(10)
            .take(5)
            .map { "${it}_xform" }
            .subscribe { println("onNext => $it") }
}

fun listOfObservables(): List<Observable<String>> = listOf(syncObservable(), syncObservable())

fun combineLatest(observables: List<Observable<String>>) {
    observables.combineLatest { it.reduce { one, two -> one + two } }.subscribe(::println)
}

fun zip(observables: List<Observable<String>>) {
    observables.zip { it.reduce { one, two -> one + two } }.subscribe(::println)
}

fun simpleObservable(): Observable<String> = (0..17).toObservable().map { "Simple $it" }

fun addToCompositeSubscription() {
    val compositeSubscription = CompositeDisposable()

    Observable.just("test")
            .delay(100, TimeUnit.MILLISECONDS)
            .subscribe()
            .addTo(compositeSubscription)

    compositeSubscription.dispose()
}