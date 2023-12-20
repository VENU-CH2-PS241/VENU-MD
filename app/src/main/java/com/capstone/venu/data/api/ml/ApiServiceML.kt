package com.capstone.venu.data.api.ml

import com.capstone.venu.data.temp.ArticleResponse
import com.capstone.venu.data.response.CheckRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceML {

    @POST("/api/v1/predict/hoax")
    suspend fun HoaxProbability(
        @Body request: CheckRequest
    ) : ArticleResponse


    @GET("/api/scrap")
    suspend fun getArticle(
        @Query("title") articleTitle: String
    ) : ArticleResponse
}