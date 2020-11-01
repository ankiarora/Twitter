package com.myprojects.twitter.network

import com.myprojects.twitter.service.TweetService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MyRetrofitBuilder {
    val BASE_URL = "https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io"

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiService: TweetService by lazy {
        retrofitBuilder
            .build()
            .create(TweetService::class.java)
    }
}