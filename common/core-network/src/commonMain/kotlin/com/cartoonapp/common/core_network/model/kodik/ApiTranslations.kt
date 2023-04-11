package com.cartoonapp.common.core_network.model.kodik

import com.cartoonapp.common.domain.model.kodik.Translations
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTranslations(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String?,
    @SerialName("count")
    val count: Long?,
) {
    fun toDomain(): Translations {
        return Translations(
            id = id,
            title = title ?: "",
            count = count ?: 0
        )
    }
}