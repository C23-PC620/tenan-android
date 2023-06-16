package com.tenan.android.data.repository

import com.tenan.android.data.Resource
import com.tenan.android.entity.request.LoginRequest
import com.tenan.android.entity.request.RegisterRequest

interface AuthRepository {
    suspend fun register(request: RegisterRequest): Resource<Nothing>
    suspend fun login(request: LoginRequest): Resource<Nothing>
    suspend fun logout(): Resource<Nothing>
}