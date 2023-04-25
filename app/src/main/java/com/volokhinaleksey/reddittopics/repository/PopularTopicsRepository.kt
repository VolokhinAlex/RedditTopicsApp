package com.volokhinaleksey.reddittopics.repository

import androidx.paging.PagingData
import com.volokhinaleksey.reddittopics.models.ui.PopularTopicUI
import kotlinx.coroutines.flow.Flow

/**
 * A repository for getting popular topics from some data source.
 */

interface PopularTopicsRepository {

    /**
     * The method for getting a list of popular topics from some data source
     * @param subreddit - The group from which you need to get a list of popular topics.
     * @param isNetworkAvailable - The parameter is responsible for which data source will be used to get data, depending on the availability of the network.
     */

    fun getPopularTopics(
        subreddit: String,
        isNetworkAvailable: Boolean
    ): Flow<PagingData<PopularTopicUI>>

}