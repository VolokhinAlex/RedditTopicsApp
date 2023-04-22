package com.volokhinaleksey.reddittopics.interactor

import androidx.paging.PagingData
import com.volokhinaleksey.reddittopics.models.ui.PopularTopicUI
import com.volokhinaleksey.reddittopics.repository.PopularTopicsRepository
import kotlinx.coroutines.flow.Flow

class MainInteractorImpl(
    private val repository: PopularTopicsRepository
) : MainInteractor {

    /**
     * Method for getting a list of popular topics
     * @param subreddit - The group from which you want to get posts
     * @param isNetworkAvailable - The network is available or not
     */

    override fun getPopularTopics(
        subreddit: String,
        isNetworkAvailable: Boolean
    ): Flow<PagingData<PopularTopicUI>> {
        return repository.getPopularTopics(
            subreddit = subreddit,
            isNetworkAvailable = isNetworkAvailable
        )
    }

}