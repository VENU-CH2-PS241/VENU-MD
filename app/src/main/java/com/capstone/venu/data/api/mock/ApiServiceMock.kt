package com.capstone.venu.data.api.mock

import com.capstone.venu.data.response.mock.ArticleDetailMockResponse
import com.capstone.venu.data.response.mock.ArticleListMockResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceMock {

    @GET("newsarticle/{id}")
    suspend fun getnewsdetail(
        @Path("id") articleId: String
    ): ArticleDetailMockResponse


    @GET("newsitem")
    suspend fun getnewslist(

    ): List<ArticleListMockResponse>
}