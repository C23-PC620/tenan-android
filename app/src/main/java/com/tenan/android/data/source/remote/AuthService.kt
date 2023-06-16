package com.tenan.android.data.source.remote

import com.tenan.android.entity.request.LoginRequest
import com.tenan.android.entity.request.RegisterRequest
import com.tenan.android.entity.response.LoginResponse
import com.tenan.android.entity.response.RefreshTokenResponse
import com.tenan.android.entity.response.RegisterResponse
import com.tenan.android.entity.response.ServerResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("user/signup")
    suspend fun register(
        @Body request: RegisterRequest
    ): ServerResponse<RegisterResponse>

    @POST("user/signin")
    suspend fun login(
        @Body request: LoginRequest
    ): ServerResponse<LoginResponse>

    @POST("user/signout")
    suspend fun logout(
        @Header("Authorization") token: String
    )

    @POST("user/token")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): ServerResponse<RefreshTokenResponse>

}