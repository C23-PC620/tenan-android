package com.tenan.android.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tenan.android.data.Resource
import com.tenan.android.data.repository.TourismRepository
import com.tenan.android.data.source.paging.SearchTourismPagingSource
import com.tenan.android.data.source.remote.TourismService
import com.tenan.android.entity.Coordinate
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.Tourism
import com.tenan.android.entity.request.RecommendedTourismRequest
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class TourismDataSource @Inject constructor(
    private val tourismService: TourismService
) : TourismRepository {

    override fun searchTourism(query: String, city: String): Flow<PagingData<Tourism>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                SearchTourismPagingSource(
                    query = query,
                    city = city,
                    tourismService = tourismService
                )
            }
        ).flow
    }

    override suspend fun getTourismById(tourismId: Int): Resource<Tourism> {
        return try {
            tourismService.getTourismById(tourismId).let { response ->
                Resource.Success(response.data)
            }
        } catch (e: Exception) {
            Resource.Failed()
        }
    }

    override suspend fun getRecommendedTourism(city: String): Resource<List<Tourism>> {
        return try {
            Timber.i("try to get data: $city")
            tourismService.getRecommendedTourism(RecommendedTourismRequest(city)).let { response ->
                Timber.i("getRecommended: $response")
                Resource.Success(response.data)
            }
        } catch (e: Exception) {
            Timber.e(e)
            Resource.Failed()
        }
    }

    override suspend fun getRecommendedHotel(coordinate: Coordinate): Resource<List<Hotel>> {
        TODO("Not yet implemented")
    }

}