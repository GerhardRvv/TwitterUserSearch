package com.gerhard.twittersearch.search.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerhard.twittersearch.R
import com.gerhard.twittersearch.core.component.CtaCustomButton
import com.gerhard.twittersearch.core.component.CustomOutlinedTextField
import com.gerhard.twittersearch.search.viewmodel.SearchUserViewModel
import com.gerhard.twittersearch.search.viewmodel.TwitterUserSearchUiState
import com.gerhard.twittersearch.ui.theme.TwitterAppTheme

@Composable
fun TwitterUserSearchScreen(viewModel: SearchUserViewModel) {
    val uiState = viewModel.uiState
    TwitterUserSearchContent(
        uiState = uiState,
        onSearchUserClick = { userName -> viewModel.getUser(userName) },
    )
}

@Composable
fun TwitterUserSearchContent(
    uiState: TwitterUserSearchUiState,
    onSearchUserClick: (userName: String) -> Unit,
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TwitterAppTheme.colors.bg01)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                ProvideTextStyle(value = TwitterAppTheme.typography.title) {
                    Text(
                        text = stringResource(id = R.string.twitter_search_screen_title),
                        color = TwitterAppTheme.colors.text01,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(40.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))

                ProvideTextStyle(value = TwitterAppTheme.typography.body) {
                    Text(
                        text = stringResource(id = R.string.twitter_search_screen_body),
                        color = TwitterAppTheme.colors.text01,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(40.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.size(24.dp))

                if (uiState.error) {
                    ProvideTextStyle(value = TwitterAppTheme.typography.title) {
                        Text(
                            text = stringResource(id = R.string.twitter_user_no_result),
                            color = TwitterAppTheme.colors.accent01,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(40.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                CustomOutlinedTextField { text = it }
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
        CtaCustomButton(text = text, onUserClick = { onSearchUserClick(text) })
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSearchPreview(isDarkMode: Boolean = false) {
    TwitterAppTheme(darkTheme = isDarkMode) {
        TwitterUserSearchContent(
            uiState = TwitterUserSearchUiState(error = true),
            onSearchUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSearchPreviewDark() {
    UserSearchPreview(true)
}

@Preview(showBackground = true)
@Composable
private fun UserSearchErrorPreview(isDarkMode: Boolean = false) {
    TwitterAppTheme(darkTheme = isDarkMode) {
        TwitterUserSearchContent(
            uiState = TwitterUserSearchUiState(error = false),
            onSearchUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSearchErrorPreviewDark() {
    UserSearchErrorPreview(true)
}
