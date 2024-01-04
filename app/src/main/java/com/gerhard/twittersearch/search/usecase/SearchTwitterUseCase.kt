package com.gerhard.twittersearch.search.usecase

import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.core.api.model.TwitterUser
import com.gerhard.twittersearch.search.repository.SearchUserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.last

class SearchTwitterUserUseCase @Inject constructor(
    private val repository: SearchUserRepository
) {
    suspend operator fun invoke(screenName: String): Resource<TwitterUser>? {
        val response = repository.getTwitterUser(screenName).last()
        return when (response.status) {
            Resource.Status.SUCCESS -> {
                response.data?.let { user ->
                    Resource.success(user)
                }
            }
            Resource.Status.LOADING -> {
                Resource.loading()
            }
            Resource.Status.ERROR -> {
                Resource.error(response.throwable ?: RuntimeException("Failed to load User"))
            }
        }
    }
}
