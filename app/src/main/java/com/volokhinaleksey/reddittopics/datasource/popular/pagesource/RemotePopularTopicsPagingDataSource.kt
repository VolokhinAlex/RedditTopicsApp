package com.volokhinaleksey.reddittopics.datasource.popular.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.volokhinaleksey.reddittopics.models.remote.PopularTopicDTO
import com.volokhinaleksey.reddittopics.network.RedditApiService

class RemotePopularTopicsPagingDataSource(
    private val apiService: RedditApiService,
    private val subreddit: String
) : PagingSource<String, PopularTopicDTO>() {

    override fun getRefreshKey(state: PagingState<String, PopularTopicDTO>): String? = null

    /**
     * A method for loading data dynamically when scrolling the list.
     */

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularTopicDTO> {
        return try {
            val response = apiService.getPopularTopics(
                subreddit = subreddit,
                limit = LIMIT_SIZE,
                after = params.key.orEmpty()
            )
            val data =
                response.data?.children?.map { it.data ?: PopularTopicDTO(null, null, null, null) }
                    .orEmpty()
            LoadResult.Page(data, response.data?.before, response.data?.after)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val LIMIT_SIZE = 10
    }

}