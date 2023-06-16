package com.tenan.android.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<T>(
    @SerialName("code") val code: String,
    @SerialName("status") val status: String,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("total_page") val totalPage: Int,
    @SerialName("data") val data: List<T>
)
