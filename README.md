# RxKotlin

## Kotlin Extensions for RxJava

RxKotlin is a lightweight library that adds convenient extension functions to [RxJava](https://github.com/ReactiveX/RxJava). You can use RxJava with Kotlin out-of-the-box, but Kotlin has language features (such as [extension functions](https://kotlinlang.org/docs/reference/extensions.html)) that can streamline usage of RxJava even more. RxKotlin aims to conservatively collect these conveniences in one centralized library. 


```kotlin
package rx.lang.kotlin

import rx.Observable

fun main(args: Array<String>) {

    val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")

    list.toObservable() // extension function for Iterables
            .filter { it.length >= 5 }
            .subscribeBy(  // named arguments for lambda Subscribers
                onNext = { println(it) },
                onError =  { it.printStackTrace() },
                onCompleted = { println("Done!") }
            )

}
```

## Kotlin Slack Channel

Join us on the #rx channel in Kotlin Slack!

https://kotlinlang.slack.com/messages/rx


## Build

[![Build Status](https://travis-ci.org/ReactiveX/RxKotlin.svg?branch=0.x)](https://travis-ci.org/ReactiveX/RxKotlin)

## Binaries

Binaries and dependency information for Maven, Ivy, Gradle and others can be found at [http://search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Crxkotlin).

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex</groupId>
    <artifactId>rxkotlin</artifactId>
    <version>x.y.z</version>
</dependency>
```

and for Gradle:

```groovy
compile 'io.reactivex:rxkotlin:x.y.z'
```

## Contributing

We welcome contributions and discussion for new features. It is recommended to file an issue first to prevent unnecessary efforts, but feel free to put in pull requests. The vision is to keep this library lightweight, with a tight and focused scope applicable to all platforms (including Android, server, and desktop). Anything specific to a particular domain (for example, [JavaFX](https://github.com/thomasnield/RxKotlinFX), [Math](https://github.com/thomasnield/rxkotlin-math), or [JDBC](https://github.com/davidmoten/rxjava-jdbc)) might be better suited as a separate project. Feel free to open discussion and we will help figure out where your functionality may belong. 



