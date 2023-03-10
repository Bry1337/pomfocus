// Suppress DSL_SCOPE_VIOLATION is needed to avoid a known false positive
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.android.application)
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt)

}

kotlin.sourceSets.all {
    // Suppress compile warning: This annotation should be used with the compiler argument '-opt-in=kotlin.RequiresOptIn'
    // https://discuss.kotlinlang.org/t/how-to-supress-optin-warnings-in-gradle-and-during-git-commit-code-analysis/17981/6
    languageSettings.optIn("kotlin.RequiresOptIn")
}

android {
    namespace = "io.bry1337.pomfocus.android"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        applicationId = "io.bry1337.pomfocus.android"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }

    // compose static functions
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    // Compose
    implementation(libs.bundles.compose)
    // Accompanist
    implementation(libs.bundles.accompanist)
    // Hilt
    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

    // Core / Notification Compat
    implementation(libs.androidx.core)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}