package com.tenan.android.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("user_id") val userId: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String
)
