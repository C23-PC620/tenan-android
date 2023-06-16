package com.tenan.android.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedTourismRequest(
    @SerialName("city") val city: String
)
