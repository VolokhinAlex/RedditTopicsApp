package com.volokhinaleksey.reddittopics.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.volokhinaleksey.reddittopics.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * A screen for displaying a list of popular topics of the group on credit.
 */

@Composable
fun PopularTopicsScreen(viewModel: MainViewModel = koinViewModel(), networkAvailable: Boolean) {
    val popularTopics = viewModel.getHotTopics(subreddit = "aww", isOnline = networkAvailable)
        .collectAsLazyPagingItems()

    LazyColumn {
        items(popularTopics.itemCount) {
            popularTopics[it]?.let { item ->
                RedditTopicCard(popularTopicUI = item)
            }
        }
        item {
            popularTopics.apply {
                when {
                    loadState.refresh is LoadState.Loading -> LoadingProgressBar()
                    loadState.append is LoadState.Loading -> LoadingProgressBar()
                    loadState.refresh is LoadState.Error -> {
                        val message = popularTopics.loadState.refresh as LoadState.Error
                        ErrorMessage(message = message.error.localizedMessage.orEmpty())
                    }

                    loadState.append is LoadState.Error -> {
                        val message = popularTopics.loadState.append as LoadState.Error
                        ErrorMessage(message = message.error.localizedMessage.orEmpty())
                    }
                }
            }
        }
    }
}