package com.tenan.android.data.source.remote

import com.tenan.android.entity.User
import com.tenan.android.entity.response.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("user/my-profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): ServerResponse<User>

}