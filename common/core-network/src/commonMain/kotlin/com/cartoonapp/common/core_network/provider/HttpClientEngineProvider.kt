package com.cartoonapp.common.core_network.provider

import io.ktor.client.engine.HttpClientEngine

expect class HttpClientEngineProvider() {
    fun get(): HttpClientEngine
}