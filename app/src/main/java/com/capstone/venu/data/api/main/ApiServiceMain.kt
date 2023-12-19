package com.capstone.venu.data.api.main

import com.capstone.venu.data.CategoriesResponse
import com.capstone.venu.data.NewsResponse
import com.capstone.venu.data.PredictionsResponse
import com.capstone.venu.data.RecomendationsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceMain {

    @GET("/api/categories")
    fun getCategories(

    ): CategoriesResponse

    @GET("/api/news1s")
    fun getNews1s(

    ): NewsResponse

    @GET("/api/predictions-news")
    fun getPredictionNews(

    ): PredictionsResponse

    @GET("/api/recommendations")
    fun getRecommendations(

    ): RecomendationsResponse

