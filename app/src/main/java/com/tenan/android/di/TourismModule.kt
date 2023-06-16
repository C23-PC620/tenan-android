package com.tenan.android.di

import com.tenan.android.data.repository.TourismRepository
import com.tenan.android.data.source.TourismDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TourismModule {
    @Binds
    abstract fun bindTourismRepository(source: TourismDataSource): TourismRepository
}