package com.tenan.android.data.source.remote

import com.tenan.android.entity.Coordinate
import com.tenan.android.entity.Hotel
import com.tenan.android.entity.Tourism
import com.tenan.android.entity.request.RecommendedTourismRequest
import com.tenan.android.entity.response.PagingResponse
import com.tenan.android.entity.response.ServerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TourismService {

    @GET("tourisms")
    suspend fun searchTourism(
        @Query("q") query: String?,
        @Query("city") city: String?,
        @Query("page") page: Int
    ): PagingResponse<Tourism>

    @GET("tourisms/{tourism_id}")
    suspend fun getTourismById(
        @Path("tourism_id") tourismId: Int
    ): ServerResponse<Tourism>

    @GET("tourisms/{tourism_id}")
    suspend fun getTourismByIdWithAuth(
        @Path("tourism_id") tourismId: Int,
        @Header("Authorization") token: String
    ): ServerResponse<Tourism>

    @POST("tourisms/recommendedTourisms")
    suspend fun getRecommendedTourism(
        @Body request: RecommendedTourismRequest
    ): ServerResponse<List<Tourism>>

    @GET("tourisms/recommendedHotels")
    suspend fun getRecommendedHotels(
        @Body coordinate: Coordinate
    ): ServerResponse<List<Hotel>>

}