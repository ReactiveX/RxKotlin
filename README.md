# Kotlin Adaptor for RxJava

This adaptor exposes a set of Extension functions that allow a more idiomatic Kotlin usage

```kotlin
    observable<String> { subscriber ->
        subscriber.onNext("H")
        subscriber.onNext("e")
        subscriber.onNext("l")
        subscriber.onNext("")
        subscriber.onNext("l")
        subscriber.onNext("o")
        subscriber.onCompleted()
    }.filter { it.isNotEmpty() }.
    fold (StringBuilder()) { sb, e -> sb.append(e) }.
    map { it.toString() }.
    subscribe { result ->
      a.received(result)
    }

    verify(a, times(1)).received("Hello")
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
