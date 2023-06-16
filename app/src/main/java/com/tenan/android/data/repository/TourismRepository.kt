package com.tenan.android.data.repository

import androidx.paging.PagingData
import com.tenan.android.data.Resource
import com.tenan.android.entity.Coordinate
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismRepository {
    suspend fun searchTourism(query: String?, city: String?): Flow<PagingData<Tourism>>
    suspend fun getTourismById(tourismId: Int): Resource<Tourism>
    suspend fun getRecommendedTourism(city: String): Resource<List<Tourism>>
    suspend fun getRecommendedHotel(coordinate: Coordinate): Resource<List<Hotel>>
}