package com.capstone.venu.data.api.ml

import com.capstone.venu.data.response.ml.ArticleResponse
import com.capstone.venu.data.response.ml.PredictionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceML {

    @GET("/api/scrap")
    suspend fun getDetailArticle(
        @Query("title") articleTitle: String
    ): ArticleResponse

    @GET("/api/scrap")
    suspend fun getListArticle(
    ): List<ArticleResponse>

    @POST("/api/v1/predict/hoax")
    suspend fun predictHoax(
    ): PredictionResponse

}