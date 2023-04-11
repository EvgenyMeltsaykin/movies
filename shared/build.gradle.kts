plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
    id("app.cash.sqldelight") version "2.0.0-alpha05"
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.cartoonapp.animemovies.db")
        }
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    targets
        .filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
        .filter { it.konanTarget.family == org.jetbrains.kotlin.konan.target.Family.IOS }
        .forEach { target ->
            target.binaries {
                framework {
                    baseName = "Shared"
                    export(projects.common.coreNetwork)
                    export(libs.decompose)
                    export(libs.essentyLifecycle)
                    export(libs.multiplatformSettings)
                    export(libs.napier)
                }
            }
        }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.common.coreNetwork)
                implementation(projects.common.coreKoin)
                implementation(projects.common.core)
                implementation(projects.common.domain)
                implementation(projects.feature.tabNavigation)

                implementation(libs.decompose)
                implementation(libs.essentyLifecycle)
                implementation(libs.koinCore)
                implementation(libs.kotlinCoroutines)
                implementation(libs.ktorCore)
                implementation(libs.kotlinxSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {}
        val androidUnitTest by getting
        val iosMain by creating {
            dependencies {
                api(projects.common.coreNetwork)
                api(libs.decompose)
                api(libs.essentyLifecycle)
                api(libs.koinCore)
                api(libs.multiplatformSettings)
                api(libs.napier)
            }
            dependsOn(commonMain)

        }
        val iosTest by creating {
            dependsOn(commonTest)
        }
    }
}

android {
    namespace = "com.cartoonapp.animemovies"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}