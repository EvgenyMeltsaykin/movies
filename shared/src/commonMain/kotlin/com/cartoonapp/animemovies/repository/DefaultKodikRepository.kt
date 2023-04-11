package com.cartoonapp.animemovies.repository

import com.cartoonapp.common.core_network.api.KodikApi
import com.cartoonapp.common.domain.model.kodik.Translations
import com.cartoonapp.common.domain.repository.KodikRepository

class DefaultKodikRepository(
    private val kodikApi: KodikApi
) : KodikRepository {
    override suspend fun getTranslations(): List<Translations> {
        return kodikApi.getTranslations().results.map { it.toDomain() }.subList(0, 5)
    }
}