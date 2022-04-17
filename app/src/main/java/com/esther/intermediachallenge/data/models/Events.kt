package com.esther.intermediachallenge.data.models

import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("start") val start: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
)


