package com.tenan.android.data.source

import com.tenan.android.data.Resource
import com.tenan.android.data.repository.AuthRepository
import com.tenan.android.entity.request.LoginRequest
import com.tenan.android.entity.request.RegisterRequest

class AuthDataSource : AuthRepository {

    override suspend fun register(request: RegisterRequest): Resource<Nothing> {
        TODO("Not yet implemented")
    }

    override suspend fun login(request: LoginRequest): Resource<Nothing> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Resource<Nothing> {
        TODO("Not yet implemented")
    }

}