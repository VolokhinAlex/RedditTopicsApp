package com.volokhinaleksey.reddittopics.datasource.popular

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.volokhinaleksey.reddittopics.datasource.popular.pagesource.RemotePopularTopicsPagingDataSource
import com.volokhinaleksey.reddittopics.models.remote.PopularTopicDTO
import com.volokhinaleksey.reddittopics.network.ApiHolder
import kotlinx.coroutines.flow.Flow

class RemotePopularTopicsDataSource(
    private val apiHolder: ApiHolder
) : PopularTopicsDataSource<PopularTopicDTO> {

    /**
     * Method for getting popular posts from reddit
     * @param subreddit - The group from which you want to get posts
     */

    override fun getPopularTopics(subreddit: String): Flow<PagingData<PopularTopicDTO>> {
        return Pager(PagingConfig(
            pageSize = PopularTopicsDataSource.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = PopularTopicsDataSource.INITIAL_LOAD_SIZE
        ), pagingSourceFactory = {
            RemotePopularTopicsPagingDataSource(apiService = apiHolder.redditApiService, subreddit = subreddit)
        }).flow
    }
}