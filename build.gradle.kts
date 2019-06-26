@file:Suppress("UNUSED_VARIABLE", "HasPlatformType")

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    kotlin("jvm") version "1.3.40"
    id("maven-publish")
}

repositories {
    jcenter()
}

group = "io.reactivex.rxjava2"
version = "2.4.0-SNAPSHOT"

sourceSets {
    val examples by creating {
        java {
            compileClasspath += sourceSets.main.get().output
            runtimeClasspath += sourceSets.main.get().output
        }
    }
}

val examplesImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

dependencies {
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    testImplementation("org.funktionale:funktionale-partials:1.0.0-final")
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:1.10.19")

    examplesImplementation("com.squareup.retrofit2:retrofit:2.6.0")
    examplesImplementation("com.squareup.retrofit2:adapter-rxjava2:2.6.0")
    examplesImplementation("com.squareup.retrofit2:converter-moshi:2.6.0")
}

val sourcesJar by tasks.creating(Jar::class) {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("full") {
            artifactId = "rxkotlin"

            from(components["java"])
            artifact(sourcesJar)

            pom {
                name.set("RxKotlin")
                description.set("RxJava bindings for Kotlin")
                url.set("https://github.com/ReactiveX/RxKotlin")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
            }
        }
    }
    repositories {
        //todo
        /*maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri("...")
            val snapshotsRepoUrl = uri("...")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }*/
    }
}

// support for snapshot/final releases with the various branches RxJava uses
//nebulaRelease {
//    addReleaseBranchPattern(/\d+\.\d+\.\d+/)
//    addReleaseBranchPattern('HEAD')
//}

//if (project.hasProperty('release.useLastTag')) {
//    tasks.prepare.enabled = false
//}
