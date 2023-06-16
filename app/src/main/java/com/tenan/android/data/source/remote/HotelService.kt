package com.tenan.android.data.source.remote

import com.tenan.android.entity.Hotel
import com.tenan.android.entity.HotelDetail
import com.tenan.android.entity.response.PagingResponse
import com.tenan.android.entity.response.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelService {

    @GET("lodgings")
    suspend fun getHotels(
        @Query("q") query: String?,
        @Query("city") city: String?,
        @Query("page") page: Int
    ): PagingResponse<Hotel>

    @GET("lodgings/{lodging_id}")
    suspend fun getHotelById(
        @Path("lodging_id") lodgingId: Int
    ): ServerResponse<HotelDetail>

    @GET("lodgings/{lodging_id}")
    suspend fun getHotelByIdWithAuth(
        @Path("lodging_id") lodgingId: Int,
        @Header("Authorization") token: String
    ): ServerResponse<HotelDetail>

}