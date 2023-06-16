package com.tenan.android.data.source

import androidx.paging.PagingData
import com.tenan.android.data.Resource
import com.tenan.android.data.repository.HotelRepository
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.HotelDetail
import kotlinx.coroutines.flow.Flow

class HotelDataSource : HotelRepository {

    override suspend fun getHotels(): Flow<PagingData<Hotel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHotelById(hotelId: Int): Resource<HotelDetail> {
        TODO("Not yet implemented")
    }

}