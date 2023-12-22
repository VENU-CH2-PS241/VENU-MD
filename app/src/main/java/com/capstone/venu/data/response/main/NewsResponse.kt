package com.capstone.venu.data.response.main

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("data")
    val data: List<NewsItem>
)

data class NewsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: NewsAttributes
)

data class NewsAttributes(
    @SerializedName("id_news2")
    val idNews2: String?,

    @SerializedName("news_headline1")
    val newsHeadline1: String?,

    @SerializedName("news_headline2")
    val newsHeadline2: String?,

    @SerializedName("content")
    val content: String,

    @SerializedName("resource")
    val resource: String,

    @SerializedName("like")
    val like: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("publishedAt")
    val publishedAt: String
)