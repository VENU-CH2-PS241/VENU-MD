package com.capstone.venu.data.api.news

import com.capstone.venu.data.response.mock.ArticleDetailMockResponse
import com.capstone.venu.data.response.mock.ArticleListMockResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceNews {

    @GET("newsarticle/{id}")
    suspend fun getnewsdetail(
        @Path("id") articleId: String
    ): ArticleDetailMockResponse


    @GET("newsitem")
    suspend fun getnewslist(

    ): List<ArticleListMockResponse>
}