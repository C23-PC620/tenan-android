package com.tenan.android.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse<T>(
    @SerialName("code") val code: String,
    @SerialName("status") val status: String,
    @SerialName("data") val data: T
)
