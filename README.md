# Kotlin Adaptor for RxJava

This adaptor exposes a set of Extension functions that allow a more idiomatic Kotlin usage

```kotlin
Observable.create<String> { subscriber ->
    subscriber.apply {
        onNext("H")
        onNext("e")
        onNext("l")
        onNext("")
        onNext("l")
        onNext("o")
        onCompleted()
    }
}
.filter { it.isNotEmpty() }
.joinToString()
.subscribe { a.received(it) }

verify(a, times(1)).received("Hello")
```

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

and for Ivy:

```xml
<dependency org="io.reactivex" name="rxkotlin" rev="x.y.z" />
```

and for Gradle:

```groovy
compile 'io.reactivex:rxkotlin:x.y.z'
```
