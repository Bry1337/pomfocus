// Suppress DSL_SCOPE_VIOLATION is needed to avoid a known false positive
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinter) apply true

    // ./gradlew dependencyUpdates
    // Report: build/dependencyUpdates/report.txt
    alias(libs.plugins.gradle.versions) apply true
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    kotlin(findProperty("kotlin.android") as String)
        .version(findProperty("kotlin.multiplatform.version") as String)
        .apply(false)
    kotlin(findProperty("kotlin.multiplatform") as String)
        .version(findProperty("kotlin.multiplatform.version") as String)
        .apply(false)
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.bundles.plugins)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // https://github.com/chRyNaN/platform
        maven("https://repo.repsy.io/mvn/chrynan/public")
    }
}

//https://github.com/ben-manes/gradle-versions-plugin#rejectversionsif-and-componentselection
fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}