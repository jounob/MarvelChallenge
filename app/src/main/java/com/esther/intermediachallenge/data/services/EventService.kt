package com.esther.intermediachallenge.data.services

import com.esther.intermediachallenge.data.models.ApiResponse
import com.esther.intermediachallenge.data.models.Data
import com.esther.intermediachallenge.data.models.Events
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EventService {

    @GET("events")
    suspend fun getEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("limit") limit:Int,
        @Query("orderBy") orderBy: String,
    ): Response<ApiResponse<Data<List<Events>>>>
}