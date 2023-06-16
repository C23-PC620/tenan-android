package com.tenan.android.data.source

import com.tenan.android.data.Resource
import com.tenan.android.data.repository.UserRepository
import com.tenan.android.entity.User

class UserDataSource : UserRepository {

    override suspend fun getUserProfile(): Resource<User> {
        TODO("Not yet implemented")
    }

}