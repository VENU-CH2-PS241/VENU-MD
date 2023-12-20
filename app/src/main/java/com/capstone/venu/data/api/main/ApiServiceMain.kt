package com.capstone.venu.data.api.main

import com.capstone.venu.data.temp.NewsArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceMain {
    @GET("newsarticle")
    suspend fun getNewsArticles(

    ): Response<NewsArticleResponse>
}

