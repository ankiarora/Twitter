package com.myprojects.twitter.viewModel

import androidx.lifecycle.LiveData
import com.example.loadmorerecyclerviewpoc.repository.TwitterRepository
import com.myprojects.twitter.model.TweetResponse

open class HomeScreenViewModel : BaseViewModel() {

    fun getTweets(): LiveData<TweetResponse> =
        TwitterRepository.getTweets()
}