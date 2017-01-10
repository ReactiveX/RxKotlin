import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.*

val main = project {
    name = "RxKotlin"
    group = "reactivex"
    artifactId = name
    version = "0.1"

    dependencies {
        compile("io.reactivex:rxjava:1.1.1")
    }

    dependenciesTest {
        compile("junit:junit:4.12", "org.mockito:mockito-core:1.8.5", "org.funktionale:funktionale:0.8")
    }

    assemble {
        mavenJars {}
    }
}

val examples = project(main) {
    name = "RxKotlin-Examples"
    group = "reactivex"
    artifactId = name
    version = "0.1"
    directory = "examples"

    dependenciesTest {
        compile("junit:junit:4.12")
    }

    assemble {
        mavenJars {}
    }
}