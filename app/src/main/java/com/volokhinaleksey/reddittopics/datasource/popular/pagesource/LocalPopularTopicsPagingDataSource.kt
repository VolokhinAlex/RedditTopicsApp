package com.volokhinaleksey.reddittopics.datasource.popular.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.volokhinaleksey.reddittopics.models.local.PopularTopicEntity
import com.volokhinaleksey.reddittopics.room.PopularTopicDao
import kotlinx.coroutines.delay

class LocalPopularTopicsPagingDataSource(
    private val dao: PopularTopicDao,
    private val subreddit: String
) : PagingSource<Int, PopularTopicEntity>() {

    /**
     * Method of obtaining an updated key for dynamic loading
     */

    override fun getRefreshKey(state: PagingState<Int, PopularTopicEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    /**
     * A method for loading data dynamically when scrolling the list.
     */

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularTopicEntity> {
        return try {
            val page = params.key ?: 0
            val response = dao.getPopularTopics(
                limit = params.loadSize,
                offset = page * params.loadSize,
                subreddit = subreddit
            )
            if (page != 0) {
                delay(1000)
            }
            LoadResult.Page(
                data = response, prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}