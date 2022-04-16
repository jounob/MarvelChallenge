package com.esther.intermediachallenge.data.services

import com.esther.intermediachallenge.data.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CharacterService {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ApiResponse<Data<List<Character>>>>

    @GET("characters/{characterId}/comics")
    suspend fun getComicsByCharacterID(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Path("characterId") characterId: Int
    ): Response<ApiResponse<Data<List<Comic>>>>
}

//data class CharactersResponse(
//    val code: Int = 0,
//    @SerializedName("data")
//    val charactersList: CharactersList
//)
//
//data class CharactersList(
//    @SerializedName("results")
//    val characters: List<Character>
//)
