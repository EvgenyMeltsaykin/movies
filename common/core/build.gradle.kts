plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinCoroutines)
                implementation(libs.decompose)
                api(libs.kotlinxDateTime)
                api(libs.napier)
            }
        }
    }
}

android {
    namespace = "com.cartoonapp.common.core"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}