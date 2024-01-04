package com.gerhard.twittersearch.core.utils

import com.gerhard.twittersearch.core.api.model.Tweet
import com.gerhard.twittersearch.core.api.model.TwitterUser

object TwitterDataTestUtil {

    fun userDetails() = TwitterUser(
        id = 123456789,
        name = "Gerhard Reyes",
        screenName = "Gerhard_rvv",
        description = "I'm a software engineer and coffee addict",
        followersCount = 1000,
        friendsCount = 500,
        statusesCount = 2000,
        createdDate = "January 1, 2015",
        profileImageUrl = "http://pbs.twimg.com/profile_images/1518668201009295360/UmalxS2N_normal.jpg",
        profileBannerUrl = "https://pbs.twimg.com/profile_banners/22412376/1679051411"
    )

    fun tweetDetails() = Tweet(
        text = "This is a tweet text",
        createdAt = "January 1, 2015",
        retweetCount = 1,
        favoriteCount = 10
    )
}