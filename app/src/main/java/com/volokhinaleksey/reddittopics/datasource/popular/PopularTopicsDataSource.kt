package com.volokhinaleksey.reddittopics.datasource.popular

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * Abstract data source for obtaining information locally or remotely.
 */

interface PopularTopicsDataSource<T : Any> {

    /**
     * Method for getting popular posts from reddit
     * @param subreddit - The group from which you want to get posts
     */

    fun getPopularTopics(subreddit: String): Flow<PagingData<T>>

    companion object {
        const val PAGE_SIZE = 10
        const val INITIAL_LOAD_SIZE = 10
    }
}