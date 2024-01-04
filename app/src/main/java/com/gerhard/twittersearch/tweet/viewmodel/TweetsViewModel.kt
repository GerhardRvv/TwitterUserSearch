package com.gerhard.twittersearch.tweet.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.core.api.model.Tweet
import com.gerhard.twittersearch.core.api.model.TwitterUser
import com.gerhard.twittersearch.tweet.usecase.GetTweetsUseCase
import com.gerhard.twittersearch.tweet.fragment.UserDetailsFragmentNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

private const val KEY_ARGS = "args"

@HiltViewModel
class TweetsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTweetsUseCase: GetTweetsUseCase
) : ViewModel() {

    private val userArgs: UserDetailsFragmentNavArgs = savedStateHandle[KEY_ARGS]!!

    var uiState by mutableStateOf(
        UserDetailsUiState(
            user = userArgs.user,
            tweet = Tweet("", "", 0, 1)
        )
    )

    fun refreshState() {
        getUserTweets(userArgs.user.id)
    }

    private fun getUserTweets(userId: Long, count: Int = 1) {
        viewModelScope.launch {
            uiState = uiState.copy(showTweet = false)
            val response = getTweetsUseCase(userId, count)
            if (response != null) {
                when (response.status) {
                    Resource.Status.SUCCESS -> {
                        response.data?.let {
                            uiState = uiState.copy(
                                tweet = response.data,
                                showTweet = true
                            )
                        }
                    }
                    Resource.Status.LOADING -> {}
                    Resource.Status.ERROR -> {
                    }
                }
            }
        }
    }
}

data class UserDetailsUiState(
    val user: TwitterUser,
    val tweet: Tweet,
    val showTweet: Boolean = false
)