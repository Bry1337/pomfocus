plugins {
    id("com.android.application")
    kotlin("android")
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
    implementation(libs.bundles.compose)
}