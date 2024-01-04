package com.gerhard.twittersearch.search.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerhard.twittersearch.core.utils.Resource
import com.gerhard.twittersearch.core.utils.SingleLiveEvent
import com.gerhard.twittersearch.core.api.model.TwitterUser
import com.gerhard.twittersearch.search.usecase.SearchTwitterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val getUserUseCase: SearchTwitterUserUseCase
) : ViewModel() {

    val navigationEvent = SingleLiveEvent<TwitterUser?>()
    var uiState by mutableStateOf(
        TwitterUserSearchUiState()
    )

    fun getUser(userName: String) {
        uiState = uiState.copy(loading = false, error = false)
        viewModelScope.launch {
            val response = getUserUseCase(userName)
            if (response != null) {
                when (response.status) {
                    Resource.Status.SUCCESS -> {
                        navigationEvent.value = response.data
                    }
                    Resource.Status.ERROR -> {
                        uiState = uiState.copy(
                            userName = userName,
                            error = true
                        )
                    }
                    Resource.Status.LOADING -> {
                        uiState = uiState.copy(loading = true)
                    }
                }
            }
            uiState = uiState.copy(loading = false)
        }
    }
}

data class TwitterUserSearchUiState(
    val userName: String? = null,
    val loading: Boolean = false,
    val error: Boolean = false
)





