package com.tenan.android.data.repository

import androidx.paging.PagingData
import com.tenan.android.data.Resource
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.HotelDetail
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    suspend fun getHotels(): Flow<PagingData<Hotel>>
    suspend fun getHotelById(hotelId: Int): Resource<HotelDetail>
}