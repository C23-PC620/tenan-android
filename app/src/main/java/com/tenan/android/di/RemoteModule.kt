package com.tenan.android.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tenan.android.BuildConfig
import com.tenan.android.data.source.remote.AuthService
import com.tenan.android.data.source.remote.HotelService
import com.tenan.android.data.source.remote.TourismService
import com.tenan.android.data.source.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(jsonConfig.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .client(okHttpClient)
            .build()


    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideHotelService(retrofit: Retrofit): HotelService =
        retrofit.create(HotelService::class.java)

    @Provides
    fun provideTourismService(retrofit: Retrofit): TourismService =
        retrofit.create(TourismService::class.java)

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    private const val CONTENT_TYPE = "application/json"
    private val jsonConfig = Json { ignoreUnknownKeys = true }

}