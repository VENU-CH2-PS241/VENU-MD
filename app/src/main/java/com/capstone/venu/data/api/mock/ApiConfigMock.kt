package com.capstone.venu.data.api.mock

import com.capstone.venu.BuildConfig
import com.capstone.venu.data.api.main.ApiServiceMain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfigMock {

    companion object {
        private const val BASE_URL = "https://65822c7c02f747c836791f6a.mockapi.io/api/"

        fun getMockApi(): ApiServiceMock {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiServiceMock::class.java)
        }
    }
}