package com.capstone.venu.data.response.main

import com.google.gson.annotations.SerializedName

data class CategoriesIdReponse(
    @SerializedName("data")
    val data: SingleDataItem

)

data class SingleDataItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: SingleAttributes
)

data class SingleAttributes(
    @SerializedName("topic")
    val topic: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("publishedAt")
    val publishedAt: String
)