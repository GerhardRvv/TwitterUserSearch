package com.gerhard.twittersearch.tweet.usecase

import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.core.api.model.Tweet
import com.gerhard.twittersearch.tweet.repository.TwitterTweetRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.last

class GetTweetsUseCase @Inject constructor(
    private val repository: TwitterTweetRepository,
) {
    suspend operator fun invoke(userId: Long, count: Int): Resource<Tweet>? {
        val response = repository.getLastTweetFromUser(userId, count).last()
        return when (response.status) {
            Resource.Status.SUCCESS -> {
                response.data?.let { tweet ->
                    Resource.success(tweet)
                }
            }
            Resource.Status.LOADING -> {
                Resource.loading()
            }
            Resource.Status.ERROR -> {
                Resource.error(response.throwable ?: RuntimeException("Failed to fetch Tweet"))
            }
        }
    }
}
