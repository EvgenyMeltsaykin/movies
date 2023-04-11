plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
                implementation(projects.common.core)
                implementation(projects.common.domain)
                implementation(libs.kotlinCoroutines)
                implementation(libs.bundles.ktor)
                implementation(libs.kotlinxSerialization)
                implementation(libs.kotlinxDateTime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktorOkHttp)
            }
        }
        val androidUnitTest by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.ktorDarwin)
            }
            dependsOn(commonMain)
        }
        val iosTest by creating {
            dependsOn(commonTest)
        }
    }
}

android {
    namespace = "com.cartoonapp.common.core_network"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}