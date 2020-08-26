# RxKotlin

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.reactivex.rxjava3/rxkotlin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.reactivex.rxjava3/rxkotlin)

## Kotlin Extensions for RxJava

RxKotlin is a lightweight library that adds convenient extension functions to [RxJava](https://github.com/ReactiveX/RxJava). You can use RxJava with Kotlin out-of-the-box, but Kotlin has language features (such as [extension functions](https://kotlinlang.org/docs/reference/extensions.html)) that can streamline usage of RxJava even more. RxKotlin aims to conservatively collect these conveniences in one centralized library, and standardize conventions for using RxJava with Kotlin. 


```kotlin
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable

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

## Contributing

Since Kotlin makes it easy to implement extensions for anything and everything, this project has to be conservative in what features are in scope. Intentions to create syntactic sugar can quickly regress into [syntactic saccharin](https://en.wikipedia.org/wiki/Syntactic_sugar#Syntactic_saccharin), and such personal preferences belong in one's internal domain rather than an OSS library. 

Here are some basic guidelines to determine whether your contribution might be in scope for RxKotlin: 

* Is this intended feature already in RxJava?
	- If no, propose the operator in RxJava. 
	- If yes, can Kotlin streamline the operator further? 
	
* Does this substantially reduce the amount of boilerplate code?
* Does this make an existing operator easier to use?
* Does RxJava not contain this feature due to Java language limitations, or because of a deliberate decision to not include it?

### Kotlin Slack Channel

Join us on the #rx channel in Kotlin Slack!

https://kotlinlang.slack.com/messages/rx


## Support for RxJava 3.x, RxJava 2.x and RxJava 1.x

**Use RxKotlin 3.x versions to target RxJava 3.x.**
- The 3.x version is active.

Use RxKotlin 2.x versions to target RxJava 2.x.
- The 2.x version of RxJava and RxKotlin is in maintenance mode and will be supported only through bugfixes. No new features or behavior changes will be accepted or applied.

Use RxKotlin 1.x versions to target RxJava 1.x.
- The 1.x version of RxJava and RxKotlin reached end-of-life. No further development, support, maintenance, PRs or updates will happen.

The maintainers do not update the RxJava dependency version for every minor or patch RxJava release, so you should explicitly add the desired RxJava dependency version to your `pom.xml` or `build.gradle(.kts)`.

## Binaries

Binaries and dependency information for Maven, Ivy, Gradle and others can be found at [http://search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Crxkotlin).

### RxKotlin 3.x [![Build Status](https://travis-ci.org/ReactiveX/RxKotlin.svg?branch=3.x)](https://travis-ci.org/ReactiveX/RxKotlin)

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex.rxjava3</groupId>
    <artifactId>rxkotlin</artifactId>
    <version>3.x.y</version>
</dependency>
```

Example for Gradle:

```kotlin
implementation("io.reactivex.rxjava3:rxkotlin:3.x.y")
```

### RxKotlin 2.x [![Build Status](https://travis-ci.org/ReactiveX/RxKotlin.svg?branch=2.x)](https://travis-ci.org/ReactiveX/RxKotlin)

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex.rxjava2</groupId>
    <artifactId>rxkotlin</artifactId>
    <version>2.x.y</version>
</dependency>
```

Example for Gradle:

```kotlin
implementation("io.reactivex.rxjava2:rxkotlin:2.x.y")
```

### RxKotlin 1.x 

Example for Maven:

```xml
<dependency>
    <groupId>io.reactivex</groupId>
    <artifactId>rxkotlin</artifactId>
    <version>1.x.y</version>
</dependency>
```

Example for Gradle:

```kotlin
implementation("io.reactivex:rxkotlin:1.x.y")
```

### Building with JitPack

You can also use Gradle or Maven with [JitPack](https://jitpack.io/) to build directly off a snapshot, branch, or commit of this repository.

For example, to build off the 3.x branch, use this setup for Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.ReactiveX:RxKotlin:3.x-SNAPSHOT'
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
	    <version>3.x-SNAPSHOT</version>
	</dependency>
```

Learn more about building this project with JitPack [here](https://jitpack.io/#ReactiveX/RxKotlin).



## Extensions 

|Target Type|Method|Return Type|Description|
|---|---|---|---|
|BooleanArray|toObservable()|Observable<Boolean>|Turns a Boolean array into an Observable|
|ByteArray|toObservable()|Observable<Byte>|Turns a Byte array into an Observable|
|ShortArray|toObservable()|Observable<Short>|Turns a Short array into an Observable|
|IntArray|toObservable()|Observable<Int>|Turns an Int array into an Observable|
|LongArray|toObservable()|Observable<Long>|Turns a Long array into an Observable|
|FloatArray|toObservable()|Observable<Float>|Turns a Float array into an Observable|
|DoubleArray|toObservable()|Observable<Double>|Turns a Double array into an Observable|
|Array<T>|toObservable()|Observable<Double>|Turns a `T` array into an Observable|
|IntProgression|toObservable()|Observable<Int>|Turns an `IntProgression` into an Observable|
|Iterable<T>|toObservable()|Observable<T>|Turns an `Iterable<T>` into an Observable|
|Iterator<T>|toObservable()|Observable<T>|Turns an `Iterator<T>` into an Observable|
|Observable<T>|flatMapSequence()|Observable<R>|Flat maps each `T` emission to a `Sequence<R>`|
|Observable<Pair<A,B>>|toMap()|Single<Map<A,B>>|Collects `Pair<A,B>` emissions into a `Map<A,B>`|
|Observable<Pair<A,B>>|toMultimap()|`Single<Map<A, List<B>>`|Collects `Pair<A,B>` emissions into a `Map<A,List<B>>`|
|Observable<Observable<T>>|mergeAll()|Observable<T>|Merges all Observables emitted from an Observable|
|Observable<Observable<T>>|concatAll()|Observable<T>|Concatenates all Observables emitted from an Observable|
|Observable<Observable<T>>|switchLatest()|Observable<T>|Emits from the last emitted Observable|
|Observable<*>|cast()|Observable<R>|Casts all emissions to the reified type|
|Observable<*>|ofType()|Observable<R>|Filters all emissions to only the reified type|
|Iterable<Observable<out T>>|merge()|Observable<T>|Merges an Iterable of Observables into a single Observable|
|Iterable<Observable<out T>>|mergeDelayError()|Observable<T>|Merges an Iterable of Observables into a single Observable, but delays any error|
|BooleanArray|toFlowable()|Flowable<Boolean>|Turns a Boolean array into an Flowable|
|ByteArray|toFlowable()|Flowable<Byte>|Turns a Byte array into an Flowable|
|ShortArray|toFlowable()|Flowable<Short>|Turns a Short array into an Flowable|
|IntArray|toFlowable()|Flowable<Int>|Turns an Int array into an Flowable|
|LongArray|toFlowable()|Flowable<Long>|Turns a Long array into an Flowable|
|FloatArray|toFlowable()|Flowable<Float>|Turns a Float array into an Flowable|
|DoubleArray|toFlowable()|Flowable<Double>|Turns a Double array into an Flowable|
|Array<T>|toFlowable()|Flowable<Double>|Turns a `T` array into an Flowable|
|IntProgression|toFlowable()|Flowable<Int>|Turns an `IntProgression` into an Flowable|
|Iterable<T>|toFlowable()|Flowable<T>|Turns an `Iterable<T>` into an Flowable|
|Iterator<T>|toFlowable()|Flowable<T>|Turns an `Iterator<T>` into an Flowable|
|Flowable<T>|flatMapSequence()|Flowable<R>|Flat maps each `T` emission to a `Sequence<R>`|
|Flowable<Pair<A,B>>|toMap()|Single<Map<A,B>>|Collects `Pair<A,B>` emissions into a `Map<A,B>`|
|Flowable<Pair<A,B>>|toMultimap()|`Single<Map<A, List<B>>>`|Collects `Pair<A,B>` emissions into a `Map<A,List<B>>`|
|Flowable<Flowable<T>>|mergeAll()|Flowable<T>|Merges all Flowables emitted from an Flowable|
|Flowable<Flowable<T>>|concatAll()|Flowable<T>|Concatenates all Flowables emitted from an Flowable|
|Flowable<Flowable<T>>|switchLatest()|Flowable<T>|Emits from the last emitted Flowable|
|Flowable<Any>|cast()|Flowable<R>|Casts all emissions to the reified type|
|Flowable<Any>|ofType()|Flowable<R>|Filters all emissions to only the reified type|
|Iterable<Flowable<out T>>|merge()|Flowable<T>|Merges an Iterable of Flowables into a single Flowable|
|Iterable<Flowable<out T>>|mergeDelayError()|Flowable<T>|Merges an Iterable of Flowables into a single Flowable, but delays any error|
|Single<Any>|cast()|Single<R>|Casts all emissions to the reified type|
|Observable<Single<T>>|mergeAllSingles()|Observable<R>|Merges all Singles emitted from an Observable|
|Flowable<Single<T>>|mergeAllSingles()|Flowable<R>|Merges all Singles emitted from a Flowable|
|Maybe<Any>|cast()|Maybe<R>|Casts any emissions to the reified type|
|Maybe<Any>|ofType()|Maybe<R>|Filters any emission that is the reified type|
|Observable<Maybe<T>>|mergeAllMaybes()|Observable<T>|Merges all emitted Maybes|
|Flowable<Maybe<T>>|mergeAllMaybes()|Flowable<T>|Merges all emitted Maybes|
|Action|toCompletable()|Completable|Turns an `Action` into a `Completable`|
|Callable<out Any>|toCompletable()|Completable|Turns a `Callable` into a `Completable`|
|Future<out Any>|toCompletable()|Completable|Turns a `Future` into a `Completable`|
|(() -> Any)|toCompletable()|Completable|Turns a `(() -> Any)` into a `Completable`|
|Observable<Completable>|mergeAllCompletables()|Completable>|Merges all emitted Completables|
|Flowable<Completable>|mergeAllCompletables()|Completable|Merges all emitted Completables|
|Observable<T>|subscribeBy()|Disposable|Allows named arguments to construct an Observer|
|Flowable<T>|subscribeBy()|Disposable|Allows named arguments to construct a Subscriber|
|Single<T>|subscribeBy()|Disposable|Allows named arguments to construct a SingleObserver|
|Maybe<T>|subscribeBy()|Disposable|Allows named arguments to construct a MaybeObserver|
|Completable|subscribeBy()|Disposable|Allows named arguments to construct a CompletableObserver|
|Observable<T>|blockingSubscribeBy()|Unit|Allows named arguments to construct a blocking Observer|
|Flowable<T>|blockingSubscribeBy()|Unit|Allows named arguments to construct a blocking Subscriber|
|Single<T>|blockingSubscribeBy()|Unit|Allows named arguments to construct a blocking SingleObserver|
|Maybe<T>|blockingSubscribeBy()|Unit|Allows named arguments to construct a blocking MaybeObserver|
|Completable<T>|blockingSubscribeBy()|Unit|Allows named arguments to construct a blocking CompletableObserver|
|Disposable|addTo()|Disposable|Adds a `Disposable` to the specified `CompositeDisposable`|
|CompositeDisposable|plusAssign()|Disposable|Operator function to add a `Disposable` to this`CompositeDisposable`|



## SAM Helpers (made obsolete since Kotlin 1.4)

_These methods have been made obsolete with new type inference algorithm in Kotlin 1.4.
They will be removed in some future RxKotlin version._

To help cope with the [SAM ambiguity issue](https://youtrack.jetbrains.com/issue/KT-14984) when using RxJava with Kotlin, there are a number of helper factories and extension functions to workaround the affected operators. 

```
Observables.zip()
Observables.combineLatest()
Observable#zipWith()
Observable#withLatestFrom()
Flowables.zip()
Flowables.combineLatest()
Flowable#zipWith()
Flowable#withLatestFrom()
Singles.zip()
Single#zipWith()
Maybes.zip()
```

## Usage with Other Rx Libraries

RxKotlin can be used in conjunction with other Rx and Kotlin libraries, such as [RxAndroid](https://github.com/ReactiveX/RxAndroid), [RxBinding](https://github.com/JakeWharton/RxBinding), and [TornadoFX](https://github.com/edvin/tornadofx)/[RxKotlinFX](https://github.com/thomasnield/RxKotlinFX). These libraries and RxKotlin are modular, and RxKotlin is merely a set of extension functions to RxJava that can be used with these other libraries. There should be no overlap or dependency issues. 


## Other Resources

### _Learning RxJava_ Packt Book

Chapter 12 of [_Learning RxJava_](https://www.packtpub.com/application-development/learning-rxjava) covers RxKotlin and Kotlin idioms with RxJava. 

[![](https://d255esdrn735hr.cloudfront.net/sites/default/files/imagecache/ppv4_main_book_cover/B06263_cover.png)](https://www.packtpub.com/application-development/learning-rxjava) 

### _Reactive Programming in Kotlin_ Packt Book

The book [_Reactive Programming in Kotlin_](https://www.packtpub.com/application-development/reactive-programming-kotlin) mainly focuses on RxKotlin, as well as learning reactive programming with Kotlin. 

[![](https://i.imgur.com/0GjGMn5.png)](https://www.packtpub.com/application-development/reactive-programming-kotlin) 

