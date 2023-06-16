package com.tenan.android.di

import android.content.Context
import androidx.room.Room
import com.tenan.android.data.source.local.TenanDatabase
import com.tenan.android.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideTenanDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context, TenanDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideSearchHistoryDao(tenanDatabase: TenanDatabase) =
        tenanDatabase.searchHistoryDao()

    @Provides
    fun provideUserAccountDao(tenanDatabase: TenanDatabase) =
        tenanDatabase.userAccountDao()

}