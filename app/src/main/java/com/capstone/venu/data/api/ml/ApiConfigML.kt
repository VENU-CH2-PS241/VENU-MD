package com.capstone.venu.data.api.ml

import com.capstone.venu.data.api.main.ApiServiceMain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfigMain {/*

    companion object {
        private const val BASE_URL = "https://0ace-103-189-201-90.ngrok-free.app/"

        fun getApiMain(): ApiServiceML {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildCon.DEBUG) {
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

            return retrofit.create(ApiServiceML::class.java)
        }
    }*/

}