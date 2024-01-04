package com.gerhard.twittersearch.core.api.model

data class TwitterUserResponse(
    val id: Long,
    val name: String,
    val screen_name: String,
    val description: String,
    val followers_count: Int,
    val friends_count: Int,
    val statuses_count: Int,
    val created_at: String,
    val profile_image_url_https: String?,
    val profile_banner_url: String?
)
