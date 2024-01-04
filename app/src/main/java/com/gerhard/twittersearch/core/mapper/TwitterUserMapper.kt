package com.gerhard.twittersearch.core.mapper

import com.gerhard.twittersearch.core.api.model.TwitterUser
import com.gerhard.twittersearch.core.api.model.TwitterUserResponse
import com.gerhard.twittersearch.core.utils.toFormattedDate
import javax.inject.Inject

class TwitterUserMapper @Inject constructor() : Mapper<TwitterUserResponse, TwitterUser> {
    override fun map(input: TwitterUserResponse): TwitterUser {
        return TwitterUser(
            id = input.id,
            name = input.name,
            screenName = input.screen_name,
            description = input.description,
            followersCount = input.followers_count,
            friendsCount = input.friends_count,
            statusesCount = input.statuses_count,
            createdDate = input.created_at.toFormattedDate(),
            profileImageUrl = input.profile_image_url_https ?: "",
            profileBannerUrl = input.profile_banner_url ?: ""
        )
    }
}
