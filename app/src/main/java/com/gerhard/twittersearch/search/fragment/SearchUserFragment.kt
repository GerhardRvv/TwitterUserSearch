package com.gerhard.twittersearch.search.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gerhard.twittersearch.R

import com.gerhard.twittersearch.core.utils.findNavControllerSafely
import com.gerhard.twittersearch.search.viewmodel.SearchUserViewModel
import com.gerhard.twittersearch.search.ui.TwitterUserSearchScreen
import com.gerhard.twittersearch.tweet.fragment.UserDetailsFragmentNavArgs
import com.gerhard.twittersearch.ui.theme.TwitterAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUserFragment : Fragment() {

    private val viewModel: SearchUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                TwitterAppTheme {
                    TwitterUserSearchScreen(viewModel = viewModel)
                }
            }
            subscribeObservers()
        }
    }

    private fun subscribeObservers() {
        viewModel.navigationEvent.observe(
            viewLifecycleOwner
        ) { user ->
            user?.let {
                findNavControllerSafely(R.id.searchUserFragment)?.navigate(
                    SearchUserFragmentDirections.actionSearchUserFragmentToUserDetailsFragment(
                        UserDetailsFragmentNavArgs(
                            user = user
                        )
                    )
                )
            }
        }
    }
}
