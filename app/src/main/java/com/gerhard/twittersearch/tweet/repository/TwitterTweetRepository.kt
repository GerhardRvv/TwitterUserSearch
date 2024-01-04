package com.gerhard.twittersearch.tweet.repository

import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.core.api.ApiTwitterDataService
import com.gerhard.twittersearch.core.api.model.Tweet
import com.gerhard.twittersearch.core.mapper.TweetMapper
import com.gerhard.twittersearch.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TwitterTweetRepository @Inject constructor(
    private val service: ApiTwitterDataService,
    private val tweetMapper: TweetMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getLastTweetFromUser(userId: Long, count: Int): Flow<Resource<Tweet>> =
        flow {
            emit(Resource.loading(null))
            try {
                val response = service.getUserLastTweet(userId, count).last()
                val mapResponse = tweetMapper.map(response)
                emit(Resource.success(mapResponse))
            } catch (e: Exception) {
                emit(Resource.error(Throwable("Error fetching tweet"), null))
            }
        }.flowOn(dispatcher)
}