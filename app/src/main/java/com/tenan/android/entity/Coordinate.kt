package com.tenan.android.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coordinate(
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double
)
