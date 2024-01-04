package com.gerhard.twittersearch.search.repository

import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.core.api.ApiTwitterDataService
import com.gerhard.twittersearch.core.api.model.TwitterUser
import com.gerhard.twittersearch.core.mapper.TwitterUserMapper
import com.gerhard.twittersearch.di.IoDispatcher
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Singleton
class SearchUserRepository @Inject constructor(
    private val service: ApiTwitterDataService,
    private val twitterUserMapper: TwitterUserMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getTwitterUser(screenName: String): Flow<Resource<TwitterUser>> = flow {
        emit(Resource.loading(null))
        try {
            val response = service.getTwitterUser(screenName)
            val mapResponse = twitterUserMapper.map(response)
            emit(Resource.success(mapResponse))
        } catch (e: Exception) {
            print(e)
            emit(Resource.error(Throwable("Failed to fetch user"), null))
        }
    }.flowOn(dispatcher)

}
