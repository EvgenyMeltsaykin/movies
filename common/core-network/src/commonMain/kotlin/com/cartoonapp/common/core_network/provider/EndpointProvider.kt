package com.cartoonapp.common.core_network.provider

import com.cartoonapp.common.core.Environment

class EndpointProvider {
    fun getKodik(): String {
        return ENDPOINT_KODIK
    }


    fun get(): String {
        return when {
            Environment.isStaging -> ENDPOINT_DEVELOP
            Environment.isRelease -> ENDPOINT_PRODUCTION
            else -> ENDPOINT_DEVELOP
        }
    }

    private companion object {
        const val ENDPOINT_DEVELOP = ""
        const val ENDPOINT_PRODUCTION = ""
        const val ENDPOINT_KODIK = "https://kodikapi.com/"
    }
}