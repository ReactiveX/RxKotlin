@file:Suppress("UNUSED_VARIABLE", "HasPlatformType")

import org.gradle.api.publish.maven.MavenPom
import org.jetbrains.dokka.gradle.DokkaTask

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    id("java-library")
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.dokka") version "0.9.18"
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"
}

repositories {
    jcenter()
}

group = "io.reactivex.rxjava3"

//additional source sets
sourceSets {
    val examples by creating {
        java {
            compileClasspath += sourceSets.main.get().output
            runtimeClasspath += sourceSets.main.get().output
        }
    }
}

//examples configuration
val examplesImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

dependencies {
    api("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation(kotlin("stdlib"))

    testImplementation("org.funktionale:funktionale-partials:1.0.0-final")
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:1.10.19")

    examplesImplementation("com.squareup.retrofit2:retrofit:2.7.1")
    examplesImplementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0-RC8")
    examplesImplementation("com.squareup.retrofit2:converter-moshi:2.7.1")
}

//sources
val sourcesJar by tasks.creating(Jar::class) {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

//documentation
val dokka by tasks.getting(DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"

}

//documentation
val dokkaJavadoc by tasks.creating(DokkaTask::class) {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"
}

//documentation
val javadocJar by tasks.creating(Jar::class) {
    dependsOn(dokkaJavadoc)
    archiveClassifier.set("javadoc")
    from("$buildDir/javadoc")
}

//publications
val snapshot = "snapshot"
val release = "release"

publishing {

    fun MavenPom.initPom() {
        name.set("RxKotlin")
        description.set("RxJava bindings for Kotlin")
        url.set("https://github.com/ReactiveX/RxKotlin")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        scm {
            url.set("https://github.com/ReactiveX/RxKotlin.git")
        }
        developers {
            developer {
                id.set("thomasnield")
                name.set("Thomas Nield")
                email.set("thomasnield@live.com")
                organization.set("ReactiveX")
                organizationUrl.set("http://reactivex.io/")
            }
            developer {
                id.set("vpriscan")
                name.set("Vedran Prišćan")
                email.set("priscan.vedran@gmail.com")
            }
        }
    }

    publications {
        create<MavenPublication>(snapshot) {
            artifactId = "rxkotlin"
            version = "${project.version}-SNAPSHOT"

            from(components["java"])

            pom.initPom()
        }
        create<MavenPublication>(release) {
            artifactId = "rxkotlin"
            version = "${project.version}"

            from(components["java"])
            artifact(sourcesJar)
            artifact(javadocJar)

            pom.initPom()
        }
    }
}

bintray {
    user = project.findProperty("bintrayUser") as? String
    key = project.findProperty("bintrayKey") as? String

    val isRelease = project.findProperty("release") == "true"

    publish = isRelease
    override = false

    setPublications(if (isRelease) release else snapshot)

//    dryRun = true

    with(pkg) {
        userOrg = "reactivex"
        repo = "RxJava"
        name = "RxKotlin"
        setLicenses("Apache-2.0")
        setLabels("reactivex", "rxjava", "rxkotlin")
        websiteUrl = "https://github.com/ReactiveX/RxKotlin"
        issueTrackerUrl = "https://github.com/ReactiveX/RxKotlin/issues"
        vcsUrl = "https://github.com/ReactiveX/RxKotlin.git"

        with(version) {
            name = project.version.toString()
            vcsTag = project.version.toString()

            with(gpg){
                sign = true
            }

            with(mavenCentralSync) {
                sync = true
                user = project.findProperty("sonatypeUsername") as? String
                password = project.findProperty("sonatypePassword") as? String
                close = "1"
            }
        }
    }
}
