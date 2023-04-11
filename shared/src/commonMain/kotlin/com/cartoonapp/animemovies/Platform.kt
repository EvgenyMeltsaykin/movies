package com.cartoonapp.animemovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform