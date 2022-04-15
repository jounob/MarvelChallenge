package com.esther.intermediachallenge.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character (
//    @SerializedName("comics") val comics: Appearances = Appearances(),
    @SerializedName("description") val description: String = "",
//    val events: Appearances = Appearances(),
    @SerializedName("id")val id: Int = 0,
    @SerializedName("name") val name: String = "",
//    val series: Appearances = Appearances(),
//    val stories: Appearances = Appearances(),
    @SerializedName("thumbnail") val thumbnail: Thumbnail = Thumbnail(),
//    val urls: List<Url> = listOf()
) : Parcelable

data class Appearances(
    val available: Int = 0,
    val collectionURI: String = "",
    @SerializedName("items")
    val appearances: List<Appearance> = listOf(),
    val returned: Int = 0
)

data class Appearance(
    val name: String = "",
    val resourceURI: String = "",
    val type: String = ""
)

@Parcelize
data class Thumbnail(
    val extension: String = "",
    val path: String = ""
):Parcelable

data class Url(
    val type: String = "",
    val url: String = ""
)