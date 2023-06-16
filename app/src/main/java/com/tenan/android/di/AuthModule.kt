package com.tenan.android.di

import com.tenan.android.data.repository.AuthRepository
import com.tenan.android.data.source.AuthDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModule {
    @Binds
    abstract fun bindAuthRepository(source: AuthDataSource): AuthRepository
}