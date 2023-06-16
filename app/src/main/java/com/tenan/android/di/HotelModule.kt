package com.tenan.android.di

import com.tenan.android.data.repository.HotelRepository
import com.tenan.android.data.source.HotelDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HotelModule {
    @Binds
    abstract fun bindHotelRepository(source: HotelDataSource): HotelRepository
}