# Kotlin Adaptor for RxJava

Kotlin has support for SAM (Single Abstract Method) Interfaces as Functions (i.e. Java 8 Lambdas). So you could use Kotlin in RxJava without this adaptor

```kotlin
[Test]
public fun testCreate() {

  Observable.create(object:OnSubscribe<String> {
    override fun call(subscriber: Subscriber<in String>) {
      subscriber.onNext("Hello")
      subscriber.onCompleted()
    }
    }).subscribe { result ->
      a!!.received(result)
    }

    verify(a, times(1))!!.received("Hello")
}
```

(Due to a [bug in Kotlin's compiler](http://youtrack.jetbrains.com/issue/KT-4753) you can't use SAM with OnSubscribe)

This adaptor exposes a set of Extension functions that allow a more idiomatic Kotlin usage

```kotlin
[Test]
public fun testCreate() {

  {(subscriber: Subscriber<in String>) ->
    subscriber.onNext("Hello")
    subscriber.onCompleted()
    }.asObservable().subscribe { result ->
      a!!.received(result)
    }

    verify(a, times(1))!!.received("Hello")
}
```

## Binaries

Binaries and dependency information for Maven, Ivy, Gradle and others can be found at [http://search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22rxjava-kotlin%22).

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
