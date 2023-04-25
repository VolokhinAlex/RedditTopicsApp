package com.volokhinaleksey.reddittopics.interactor

import androidx.paging.PagingData
import com.volokhinaleksey.reddittopics.models.ui.PopularTopicUI
import kotlinx.coroutines.flow.Flow

interface MainInteractor {

    /**
     * Method for getting a list of popular topics
     * @param subreddit - The group from which you want to get posts
     * @param isNetworkAvailable - The network is available or not
     */

    fun getPopularTopics(
        subreddit: String,
        isNetworkAvailable: Boolean
    ): Flow<PagingData<PopularTopicUI>>
}