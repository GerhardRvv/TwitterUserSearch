package com.gerhard.twittersearch.core.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TwitterUser(
    val id: Long,
    val name: String,
    val screenName: String,
    val description: String,
    val followersCount: Int,
    val friendsCount: Int,
    val statusesCount: Int,
    val createdDate: String,
    val profileImageUrl: String,
    val profileBannerUrl: String
) : Parcelable