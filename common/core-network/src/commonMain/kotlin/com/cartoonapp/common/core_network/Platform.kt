package com.cartoonapp.common.core_network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform