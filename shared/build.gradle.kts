plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("org.jmailen.kotlinter")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor for networking client
                // https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)

                // Kotlinx Coroutines to be available on Shared common
                // https://github.com/Kotlin/kotlinx.coroutines
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)

                // Flow coroutine library for SQL Delight
                // https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/coroutines/
                implementation(libs.sqldelight.coroutines)

                // Kotlinx Datetime
                // https://github.com/Kotlin/kotlinx-datetime
                implementation(libs.kotlinx.datetime)

                // SQLDelight
                // https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/
                implementation(libs.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Ktor for networking client
                implementation(libs.ktor.client.android)
                // SQL Delight for local database
                // https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/
                implementation(libs.sqldelight.android.driver)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // SQL Delight for local database
                // https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/
                implementation(libs.sqldelight.native.driver)

                // Ktor for networking client
                // https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies
                implementation(libs.ktor.client.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "io.bry1337.pomfocus"
    compileSdk = 32
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}

sqldelight {
    database("Database") {
        packageName = "io.bry1337.pomfocus.shared.db"
    }
}