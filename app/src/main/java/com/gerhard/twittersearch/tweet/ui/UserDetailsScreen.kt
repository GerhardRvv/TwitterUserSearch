package com.gerhard.twittersearch.tweet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gerhard.twittersearch.R
import com.gerhard.twittersearch.core.component.TwitterCard
import com.gerhard.twittersearch.core.utils.TwitterDataTestUtil
import com.gerhard.twittersearch.tweet.viewmodel.TweetsViewModel
import com.gerhard.twittersearch.tweet.viewmodel.UserDetailsUiState
import com.gerhard.twittersearch.ui.theme.Shapes
import com.gerhard.twittersearch.ui.theme.TwitterAppTheme

@Composable
fun UserDetailsScreen(
    viewModel: TweetsViewModel,
    onBackClicked: () -> Unit
) {
    val uiState = viewModel.uiState
    UserDetailsScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
    )

}

@Composable
fun UserDetailsScreenContent(
    uiState: UserDetailsUiState,
    onBackClicked: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .nestedScroll(rememberNestedScrollInteropConnection())
            .background(color = TwitterAppTheme.colors.bg01)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 32.dp)
        ) {

            CircularBackButton(onClick = onBackClicked)

            Spacer(modifier = Modifier.size(12.dp))

            UserDetailsHeader(
                header = uiState.user.screenName
            )
            Spacer(modifier = Modifier.size(12.dp))
            ImageContent(uiState.user.profileBannerUrl, uiState.user.profileImageUrl)
            Spacer(modifier = Modifier.size(12.dp))

            // Name
            InformationLabel(
                fieldName = stringResource(id = R.string.user_details_name),
                fieldValue = uiState.user.name
            )
            // Screen name
            InformationLabel(
                fieldName = stringResource(id = R.string.user_details_screen_name),
                fieldValue = uiState.user.screenName
            )

            // Description
            InformationLabel(
                fieldName = stringResource(id = R.string.user_details_description),
                fieldValue = uiState.user.description
            )

            // Followers
            InformationLabel(
                fieldName = stringResource(id = R.string.user_details_followers),
                fieldValue = uiState.user.followersCount.toString()
            )
            // Created date
            InformationLabel(
                fieldName = stringResource(id = R.string.user_details_created_date),
                fieldValue = uiState.user.createdDate
            )

            if (uiState.showTweet) {
                TwitterCard(
                    userName = uiState.user.name,
                    screenName = uiState.user.screenName,
                    date = uiState.tweet.createdAt,
                    tweetText = uiState.tweet.text,
                    userProfileImage = uiState.user.profileImageUrl
                )
            }
        }
    }
}

@Composable
fun CircularBackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_button),
            contentDescription = "Back button",
            modifier = Modifier.size(34.dp),
            tint = TwitterAppTheme.colors.accent01
        )
    }
}

@Composable
private fun ImageContent(
    backgroundImage: String?,
    profileImage: String?
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backgroundImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Product image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.small)
                .size(width = 80.dp, height = 80.dp)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Product image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .size(40.dp)
        )
    }
}

@Composable
private fun InformationLabel(
    fieldName: String,
    fieldValue: String?
) {
    if (!fieldValue.isNullOrEmpty()) {
        ProvideTextStyle(value = TwitterAppTheme.typography.bodySmall) {
            Text(
                text = fieldName,
                color = TwitterAppTheme.colors.aux03
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        ProvideTextStyle(value = TwitterAppTheme.typography.body) {
            Text(
                text = "@$fieldValue",
                color = TwitterAppTheme.colors.text01
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = TwitterAppTheme.colors.accent01, thickness = 1.dp)
        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
private fun UserDetailsHeader(header: String?) {
    header?.let {
        Column {
            ProvideTextStyle(value = TwitterAppTheme.typography.title) {
                Text(
                    text = it,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 22.dp),
                    color = TwitterAppTheme.colors.text01,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(isDarkMode: Boolean = false) {
    TwitterAppTheme(darkTheme = isDarkMode) {
        UserDetailsScreenContent(
            uiState = UserDetailsUiState(
                user = TwitterDataTestUtil.userDetails(),
                tweet = TwitterDataTestUtil.tweetDetails(),
                showTweet = true
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreviewDark() {
    DefaultPreview(true)
}
