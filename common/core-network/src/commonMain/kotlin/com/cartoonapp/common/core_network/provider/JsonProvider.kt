package com.cartoonapp.common.core_network.provider

import kotlinx.serialization.json.Json

class JsonProvider {
    fun get(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            useAlternativeNames = false
        }
    }

    companion object {
        val Default = JsonProvider().get()
    }
}