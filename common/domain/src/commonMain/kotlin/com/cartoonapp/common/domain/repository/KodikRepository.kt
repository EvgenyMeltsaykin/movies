package com.cartoonapp.common.domain.repository

import com.cartoonapp.common.domain.model.kodik.Translations

interface KodikRepository {

    suspend fun getTranslations(): List<Translations>

}