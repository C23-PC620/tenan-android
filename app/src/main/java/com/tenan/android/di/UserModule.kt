package com.tenan.android.di

import com.tenan.android.data.repository.UserRepository
import com.tenan.android.data.source.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {
    @Binds
    abstract fun bindUserRepository(source: UserDataSource): UserRepository
}