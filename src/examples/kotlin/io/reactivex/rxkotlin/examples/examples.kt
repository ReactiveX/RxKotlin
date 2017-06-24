package io.reactivex.rxkotlin.examples

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.*
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    val subscription = CompositeDisposable()

    val printArticle = { art: String ->
        println("--- Article ---\n${art.substring(0, 125)}")
    }

    @Suppress("ConvertLambdaToReference")
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
            onNext = { s: String -> println("1st onNext => $s") } andThen { println("2nd onNext => $it") }
    )

    addToCompositeSubscription()
}

private fun URL.toScannerObservable() = Observable.create<String> { s ->
    this.openStream().use { stream ->
        Scanner(stream).useDelimiter("\\A")
                .toObservable()
                .subscribe { s.onNext(it) }
    }
}

fun syncObservable(): Observable<String> = Observable.create { subscriber ->
    (0..75).toObservable()
            .map { "Sync value_$it" }
            .subscribe { subscriber.onNext(it) }
}

fun asyncObservable(): Observable<String> = Observable.create { subscriber ->
    thread {
        (0..75).toObservable()
                .map { "Async value_$it" }
                .subscribe { subscriber.onNext(it) }
    }
}

fun asyncWiki(vararg articleNames: String): Observable<String> = Observable.create { subscriber ->
    thread {
        articleNames.toObservable()
                .flatMapMaybe { name -> URL("http://en.wikipedia.org/wiki/$name").toScannerObservable().firstElement() }
                .subscribe { subscriber.onNext(it) }
    }
}

fun asyncWikiWithErrorHandling(vararg articleNames: String): Observable<String> = Observable.create { subscriber ->
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

infix inline fun <T : Any> ((T) -> Unit).andThen(crossinline block: (T) -> Unit): (T) -> Unit = { this(it); block(it) }