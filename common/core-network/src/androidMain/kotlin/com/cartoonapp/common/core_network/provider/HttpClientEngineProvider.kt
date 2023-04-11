package com.cartoonapp.common.core_network.provider

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual class HttpClientEngineProvider {
    actual fun get(): HttpClientEngine {
        return OkHttp.create {
            config {

            }
        }
    }
}