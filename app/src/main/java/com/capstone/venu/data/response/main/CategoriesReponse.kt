package com.capstone.venu.data.response.main

import com.google.gson.annotations.SerializedName

data class CategoriesReponse (
    @SerializedName("data")
    val data: List<DataItem>
)

data class DataItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: AttributesCategories
)

data class AttributesCategories(
    @SerializedName("topic")
    val topic: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("publishedAt")
    val publishedAt: String
)