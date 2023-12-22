package com.capstone.venu.data.response.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleListNewsResponse(

    @SerializedName("image")
    val image: String,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: String


) : Parcelable