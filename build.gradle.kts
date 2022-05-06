import org.jetbrains.kotlin.gradle.plugin.statistics.ReportStatisticsToElasticSearch.url
import java.net.URI

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.21")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://s3.amazonaws.com/mirego-maven/public") }

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
