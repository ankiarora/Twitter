package com.myprojects.twitter.service

import com.myprojects.twitter.model.TweetResponse
import retrofit2.Call
import retrofit2.http.GET

interface TweetService {
    @GET("/tweets")
    fun getTwitterResponse(): Call<TweetResponse>
}
