plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.cartoonapp.animemovies.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.cartoonapp.animemovies.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.common.core)
    implementation(projects.common.coreNetwork)
    implementation(projects.feature.movies)
    implementation(projects.feature.tabNavigation)

    implementation(libs.androidMaterial)
    implementation(libs.androidFragmentKtx)

    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.ktorCore)
    implementation(libs.kotlinxSerialization)

    implementation(platform(libs.composeBom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeAccompanist)
    implementation(libs.composeActivity)

    implementation(libs.decompose)
    implementation(libs.decomposeComposeExtensions)

    implementation(libs.koinAndroid)
    implementation(libs.securityCryptoKtx)

    implementation(libs.yandexMetrica)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseCrashlytics)

    coreLibraryDesugaring(libs.desugarJdkLibs)

    implementation("com.google.android.exoplayer:exoplayer:2.18.5")
}