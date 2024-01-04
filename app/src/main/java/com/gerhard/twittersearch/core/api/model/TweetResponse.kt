package com.gerhard.twittersearch.core.api.model

data class TweetResponse(
    val text: String,
    val created_at: String,
    val retweet_count: Int,
    val favorite_count: Int
)