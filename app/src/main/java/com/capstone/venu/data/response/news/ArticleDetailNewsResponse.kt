package com.capstone.venu.data.response.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDetailNewsResponse(
	@SerializedName("image") val image: String,
	@SerializedName("description") val description: String,
	@SerializedName("topic") val topic: String,
	@SerializedName("source") val source: String,
	@SerializedName("id") val id: String,
	@SerializedName("title") val title: String
) : Parcelable

