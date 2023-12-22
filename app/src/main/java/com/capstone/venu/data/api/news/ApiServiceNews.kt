package com.capstone.venu.data.api.news

import com.capstone.venu.data.response.news.ArticleDetailNewsResponse
import com.capstone.venu.data.response.news.ArticleListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceNews {

    @GET("newsarticle/{id}")
    suspend fun getnewsdetail(
        @Path("id") articleId: String
    ): ArticleDetailNewsResponse


    @GET("newsitem")
    suspend fun getnewslist(

    ): List<ArticleListNewsResponse>
}