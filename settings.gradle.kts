enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://jitpack.io")
        google()
        mavenCentral()
    }
}

rootProject.name = "AnimeMovies"
include(":androidApp")
include(":shared")
include(":common:core-network")
include(":feature")
include(":common:core")
include(":common:core-koin")
include(":feature:movies")
include(":feature:tab-navigation")
include(":common:domain")
