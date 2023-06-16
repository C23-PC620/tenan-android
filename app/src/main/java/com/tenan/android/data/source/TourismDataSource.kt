package com.tenan.android.data.source

import androidx.paging.PagingData
import com.tenan.android.data.Resource
import com.tenan.android.data.repository.TourismRepository
import com.tenan.android.entity.Coordinate
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.Tourism
import kotlinx.coroutines.flow.Flow

class TourismDataSource : TourismRepository {

    override suspend fun searchTourism(query: String?, city: String?): Flow<PagingData<Tourism>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTourismById(tourismId: Int): Resource<Tourism> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommendedTourism(city: String): Resource<List<Tourism>> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecommendedHotel(coordinate: Coordinate): Resource<List<Hotel>> {
        TODO("Not yet implemented")
    }

}