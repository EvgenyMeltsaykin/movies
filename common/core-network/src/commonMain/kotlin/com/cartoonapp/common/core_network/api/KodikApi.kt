package com.cartoonapp.common.core_network.api

import com.cartoonapp.common.core_network.model.kodik.ApiTranslations
import com.cartoonapp.common.core_network.model.kodik.BaseKodikResponse

interface KodikApi {

    suspend fun getTranslations(): BaseKodikResponse<ApiTranslations>

}