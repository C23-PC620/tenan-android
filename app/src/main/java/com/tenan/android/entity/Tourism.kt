package com.tenan.android.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tourism(
    @SerialName("tourism_id") val tourismId: Int,
    @SerialName("place_name") val placeName: String,
    @SerialName("rating") val rating: Double,
    @SerialName("city") val city: String,
    @SerialName("category") val category: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("image_url") val imageUrl: String
)
