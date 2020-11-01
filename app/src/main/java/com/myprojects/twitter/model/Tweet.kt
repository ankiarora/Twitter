package com.myprojects.twitter.model

data class Tweet(
    var name: String,
    var handle: String,
    var favoriteCount: Int,
    var retweetCount: Int,
    var profileImageUrl: String,
    var text: String,
)