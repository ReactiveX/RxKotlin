package rx.lang.kotlin.examples

import rx.Observable
import rx.Subscriber
import rx.lang.kotlin.asObservable
import kotlin.concurrent.thread
import java.util.Scanner
import java.net.URL

fun main(args: Array<String>) {

    val printArticle = {(art: String) ->
        println("--- Article ---\n${art.substring(0, 125)}")
    }

    val printIt = {(it: String) -> println(it) }

    asyncObservable().subscribe(printIt)

    syncObservable().subscribe(printIt)

    simpleComposition()

    asyncWiki("Tiger", "Elephant").subscribe(printArticle)

    asyncWikiWithErrorHandling("Tiger", "Elephant").subscribe(printArticle) { e ->
        println("--- Error ---\n${e.getMessage()}")
    }

}

public fun syncObservable(): Observable<String> {
    return {(subscriber: Subscriber<in String>) ->
        @loop for (i in 0..75) {
            if (subscriber.isUnsubscribed()) {
                break@loop
            }
            subscriber.onNext("Sync value_$i")
        }
        if (!subscriber.isUnsubscribed()) {
            subscriber.onCompleted()
        }
    }.asObservable()
}

public fun asyncObservable(): Observable<String> {
    return {(subscriber: Subscriber<in String>) ->
        thread {
            @loop for (i in 0..75) {
                if (subscriber.isUnsubscribed()) {
                    break@loop
                }
                subscriber.onNext("Async value_$i")
            }
            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted()
            }
        }
        Unit
    }.asObservable()
}

public fun asyncWiki(vararg articleNames: String): Observable<String> {
    return {(subscriber: Subscriber<in String>) ->
        thread {
            @loop for (name in articleNames) {
                if (subscriber.isUnsubscribed()) {
                    break@loop
                }
                val art = Scanner(URL("http://en.wikipedia.org/wiki/$name").openStream()).useDelimiter("\\A").next()
                subscriber.onNext(art)

            }
            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted()
            }
        }
        Unit
    }.asObservable()
}

public fun asyncWikiWithErrorHandling(vararg articleNames: String): Observable<String> {
    return {(subscriber: Subscriber<in String>) ->
        thread {
            try {
                @loop for (name in articleNames) {
                    if (subscriber.isUnsubscribed()) {
                        break@loop
                    }
                    val art = Scanner(URL("http://en.wikipedia.org/wiki/$name").openStream()).useDelimiter("\\A").next()
                    subscriber.onNext(art)

                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted()
                }
            } catch(e: Exception) {
                subscriber.onError(e)
            }
        }
        Unit
    }.asObservable()
}

public fun simpleComposition() {
    asyncObservable().skip(10).take(5)
            .map { s -> "${s}_xform" }
            .subscribe { s -> println("onNext => $s") }
}