package com.cartoonapp.common.core_network.api.kodik

import com.cartoonapp.common.core_network.api.KodikApi
import com.cartoonapp.common.core_network.model.kodik.ApiTranslations
import com.cartoonapp.common.core_network.model.kodik.BaseKodikResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class DefaultKodikApi(
    private val httpClient: HttpClient
) : KodikApi {
    override suspend fun getTranslations(): BaseKodikResponse<ApiTranslations> {
        return httpClient.get {
            url {
                parameters.append("token", "7a8b0934bdff4a6b6ee5fcddb2ceff20")
                path("translations/v2")
            }
        }.body()
    }
}