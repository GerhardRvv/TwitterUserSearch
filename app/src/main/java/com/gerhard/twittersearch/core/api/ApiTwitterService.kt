package com.gerhard.twittersearch.core.api

import com.gerhard.twittersearch.core.api.model.TweetResponse
import com.gerhard.twittersearch.core.api.model.TwitterUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTwitterDataService {

    @GET("users/show.json")
    suspend fun getTwitterUser(
        @Query("screen_name") screenName: String
    ): TwitterUserResponse

    @GET("statuses/user_timeline.json")
    suspend fun getUserLastTweet(
        @Query("user_id") userId: Long,
        @Query("count") count: Int
    ): List<TweetResponse>
}
