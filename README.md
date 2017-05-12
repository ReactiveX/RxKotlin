# RxKotlin

## Kotlin Extensions for RxJava

RxKotlin is a lightweight library that adds convenient extension functions to [RxJava](https://github.com/ReactiveX/RxJava). You can use RxJava with Kotlin out-of-the-box, but Kotlin has language features (such as [extension functions](https://kotlinlang.org/docs/reference/extensions.html)) that can streamline usage of RxJava even more. RxKotlin aims to conservatively collect these conveniences in one centralized library, and standardize conventions for using RxJava with Kotlin. 


```kotlin
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {

    val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")

    list.toObservable() // extension function for Iterables
            .filter { it.length >= 5 }
            .subscribeBy(  // named arguments for lambda Subscribers
                    onNext = { println(it) },
                    onError =  { it.printStackTrace() },
                    onComplete = { println("Done!") }
            )

}
```

## Support for RxJava 1.x and RxJava 2.x

Use RxKotlin 1.x versions to target RxJava 1.x.

Use RxKotlin 2.x versions to target RxJava 2.x.


## Usage with Other Rx Libraries

RxKotlin can be used in conjunction with other Rx and Kotlin libraries, such as [RxAndroid](https://github.com/ReactiveX/RxAndroid), [RxBinding](https://github.com/JakeWharton/RxBinding), and [TornadoFX](https://github.com/edvin/tornadofx)/[RxKotlinFX](https://github.com/thomasnield/RxKotlinFX). These libraries and RxKotlin are modular, and RxKotlin is merely a set of extension functions to RxJava that can be used with these other libraries. There should be no overlap or dependency issues. 


## Build

[![Build Status](https://travis-ci.org/ReactiveX/RxKotlin.svg?branch=2.x)](https://travis-ci.org/ReactiveX/RxKotlin)


## Binaries


Binaries and dependency information for Maven, Ivy, Gradle and others can be found at [http://search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Crxkotlin).

### RxKotlin 1.x 

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex</groupId>
    <artifactId>rxkotlin</artifactId>
    <version>1.x.y</version>
</dependency>
```

and for Gradle:

```groovy
compile 'io.reactivex:rxkotlin:x.y.z'
```

### RxKotlin 2.x 

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex.rxjava2</groupId>
    <artifactId>rxkotlin</artifactId>
    <version>2.x.y</version>
</dependency>
```

and for Gradle:

```groovy
compile 'io.reactivex.rxjava2:rxkotlin:x.y.z'
```

### Building with JitPack

You can also use Gradle or Maven with [JitPack](https://jitpack.io/) to build directly off a snapshot, branch, or commit of this repository.

For example, to build off the 1.x branch, use this setup for Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.ReactiveX:RxKotlin:1.x-SNAPSHOT'
}
```

Use this setup for Maven:

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

        <dependency>
	    <groupId>com.github.ReactiveX</groupId>
	    <artifactId>RxKotlin</artifactId>
	    <version>1.x-SNAPSHOT</version>
	</dependency>
```

Learn more about building this project with JitPack [here](https://jitpack.io/#ReactiveX/RxKotlin).

## Kotlin Slack Channel

Join us on the #rx channel in Kotlin Slack!

https://kotlinlang.slack.com/messages/rx

## Contributing

We welcome contributions and discussion for new features. It is recommended to file an issue first to prevent unnecessary efforts, but feel free to put in pull requests. The vision is to keep this library lightweight, with a tight and focused scope applicable to all platforms (including Android, server, and desktop). Anything specific to a particular domain (for example, [JavaFX](https://github.com/thomasnield/RxKotlinFX), [Math](https://github.com/thomasnield/rxkotlin-math), or [JDBC](https://github.com/davidmoten/rxjava-jdbc)) might be better suited as a separate project. Feel free to open discussion and we will help figure out where your functionality may belong.



