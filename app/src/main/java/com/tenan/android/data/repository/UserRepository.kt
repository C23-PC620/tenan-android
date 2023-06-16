package com.tenan.android.data.repository

import com.tenan.android.data.Resource
import com.tenan.android.entity.User

interface UserRepository {
    suspend fun getUserProfile(): Resource<User>
}