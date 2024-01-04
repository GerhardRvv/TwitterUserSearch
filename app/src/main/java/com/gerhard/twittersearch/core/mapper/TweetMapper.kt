package com.gerhard.twittersearch.core.mapper

import com.gerhard.twittersearch.core.api.model.Tweet
import com.gerhard.twittersearch.core.api.model.TweetResponse
import com.gerhard.twittersearch.core.utils.toFormattedDate
import javax.inject.Inject

class TweetMapper @Inject constructor() : Mapper<TweetResponse, Tweet> {
    override fun map(input: TweetResponse): Tweet {
        return Tweet(
            text = input.text,
            createdAt = input.created_at.toFormattedDate(),
            retweetCount = input.retweet_count,
            favoriteCount = input.favorite_count
        )
    }
}