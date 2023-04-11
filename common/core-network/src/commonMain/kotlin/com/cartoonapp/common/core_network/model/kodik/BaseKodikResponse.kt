package com.cartoonapp.common.core_network.model.kodik

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseKodikResponse<T>(
    @SerialName("time")
    val time: String,
    @SerialName("total")
    val total: Long,
    @SerialName("results")
    val results: List<T>,
)