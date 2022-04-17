package com.esther.intermediachallenge.data.services

import com.esther.intermediachallenge.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EventService {

    @GET("events")
    suspend fun getEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit:Int,
    ): Response<ApiResponse<Data<List<Events>>>>

    @GET("events/{eventsId}/comics")
    suspend fun getComicsEvents(
        @Path ("eventId") eventId:Int,
        @QueryMap auth: HashMap<String, String>,
        @Query("limit") limit: Int
        ): Response<ApiResponse<Data<List<Comic>>>>
}