package com.capstone.venu.data.response.main

import com.google.gson.annotations.SerializedName

data class RecommendationResponse (
    @SerializedName("data")
    val data: List<ItemRecommendation>
)

data class ItemRecommendation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: AttributesRecommendation
)

data class AttributesRecommendation(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("publishedAt")
    val publishedAt: String
)