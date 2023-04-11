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
        val commonMain by getting{
            dependencies {
                implementation(libs.koinCore)
                implementation(libs.decompose)
            }
        }
    }
}

android {
    namespace = "com.cartoonapp.common.core_koin"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}