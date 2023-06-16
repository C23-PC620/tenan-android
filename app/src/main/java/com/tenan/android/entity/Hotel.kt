package com.tenan.android.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hotel(
    @SerialName("lodging_id") val lodgingId: Int,
    @SerialName("place_name") val placeName: String,
    @SerialName("rating") val rating: Float,
    @SerialName("city") val city: String,
    @SerialName("image_url") val imageUrl: String
)
