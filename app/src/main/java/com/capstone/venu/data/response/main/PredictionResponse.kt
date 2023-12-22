package com.capstone.venu.data.response.main

import com.google.gson.annotations.SerializedName

data class PredictionResponse (
    @SerializedName("data")
    val data: List<ItemPrediction>
)

data class ItemPrediction(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: AttributesPrediction
)

data class AttributesPrediction(
    @SerializedName("percentage")
    val percentage: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("publishedAt")
    val publishedAt: String
)