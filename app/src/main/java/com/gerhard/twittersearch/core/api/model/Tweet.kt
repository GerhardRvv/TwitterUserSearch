package com.gerhard.twittersearch.core.api.model

data class Tweet(
    val text: String,
    val createdAt: String,
    val retweetCount: Int,
    val favoriteCount: Int
)