package com.cartoonapp.common.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform