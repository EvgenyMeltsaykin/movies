plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
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
                implementation(projects.feature.movies)
                implementation(projects.common.core)
                implementation(projects.common.coreNetwork)
                implementation(projects.common.domain)

                implementation(libs.decompose)
                implementation(libs.kotlinCoroutines)
            }
        }
    }

}

android {
    namespace = "com.cartoonapp.feature.tab_navigation"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}