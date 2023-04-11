package com.cartoonapp.common.core_network.provider

import com.cartoonapp.common.core_network.httpConfig.commonHttpClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

class KodikHttpClientProvider(
    private val engine: HttpClientEngine,
    private val json: Json,
    private val endpointProvider: EndpointProvider,
) {
    fun get(): HttpClient {
        return HttpClient(engine) {
            commonHttpClientConfig(json)
            defaultRequest {
                url.takeFrom(
                    URLBuilder()
                        .takeFrom(endpointProvider.getKodik())
                        .apply { encodedPath += url.encodedPath }
                )
            }
        }
    }
}

interface KodikHttpClientQualifier