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
                implementation(projects.common.coreKoin)
                implementation(projects.common.core)
                implementation(projects.common.coreNetwork)
                implementation(projects.common.domain)

                implementation(libs.kotlinxSerialization)
                implementation(libs.ktorCore)
                implementation(libs.decompose)
                implementation(libs.kotlinCoroutines)
                implementation(libs.koinCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosTest by creating {
            dependsOn(commonTest)
        }
    }
}

android {
    namespace = "com.cartoonapp.feature.movies"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}