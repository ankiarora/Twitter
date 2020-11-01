package com.example.loadmorerecyclerviewpoc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.myprojects.twitter.model.TweetResponse
import com.myprojects.twitter.network.MyRetrofitBuilder
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

object TwitterRepository {
    var TAG: String = TwitterRepository.toString()
    var job: CompletableJob? = null
    fun getTweets(): LiveData<TweetResponse> {
        job = Job()
        return object : LiveData<TweetResponse>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        var call = MyRetrofitBuilder.apiService.getTwitterResponse()
                        call.enqueue(object : retrofit2.Callback<TweetResponse> {
                            override fun onFailure(
                                call: retrofit2.Call<TweetResponse>,
                                t: Throwable
                            ) {
                                Log.d(TAG, "Retrofit Error" + t.localizedMessage)
                            }

                            override fun onResponse(
                                call: retrofit2.Call<TweetResponse>,
                                response: Response<TweetResponse>
                            ) {
                                Log.d(TAG, "Retrofit Success Response" + response.body())
                                value = response.body()!!
                            }
                        })
                    }
                }
            }
        }
    }
}