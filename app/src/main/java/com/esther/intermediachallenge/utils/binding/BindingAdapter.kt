package com.esther.intermediachallenge.utils.binding

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.esther.intermediachallenge.data.models.Thumbnail


fun setImage(view: ImageView, thumbnail: Thumbnail) {
    val url = "${thumbnail.path}.${thumbnail.extension}".replace("http", "https")
    Glide.with(view.context).load(url).into(view)
}

fun View.setIsVisible(visible: Boolean?) {
    visibility = if (visible != null && visible) View.VISIBLE else View.GONE
}