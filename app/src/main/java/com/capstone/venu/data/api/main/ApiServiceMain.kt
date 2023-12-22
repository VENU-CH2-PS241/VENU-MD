package com.capstone.venu.data.api.main

import retrofit2.http.GET

interface ApiServiceMain {
    @GET("/api/categories")
    suspend fun getCategories()

    @GET("/api/categories/{id}")
    suspend fun getCategoriesId()

    @GET("/api/news1s")
    suspend fun getNews()

    @GET("/api/recommendations")
    suspend fun getRecommendations()

    @GET("/api/predictions")
    suspend fun getPredictions()


}

