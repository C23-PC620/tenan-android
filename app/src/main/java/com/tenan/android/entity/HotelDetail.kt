package com.tenan.android.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotelDetail(
    @SerialName("lodging_id") val lodgingId: Int,
    @SerialName("place_name") val placeName: String,
    @SerialName("rating") val rating: Double,
    @SerialName("address") val address: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("city") val city: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("favorited") val favorited: Boolean = false
)
