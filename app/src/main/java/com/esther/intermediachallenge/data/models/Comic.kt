package com.esther.intermediachallenge.data.models

import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("dates") val dates: List<DateComics>

)

data class DateComics(
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String,

)