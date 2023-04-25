package com.volokhinaleksey.reddittopics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volokhinaleksey.reddittopics.interactor.MainInteractor
import kotlinx.coroutines.cancel

class MainViewModel(
    private val interactor: MainInteractor
) : ViewModel() {

    /**
     * Method for getting hot topics
     * @param subreddit - The group from which you need to get a list of hot topics.
     * @param isOnline - Parameter for determining the data source
     */

    fun getHotTopics(subreddit: String, isOnline: Boolean) = interactor.getPopularTopics(
        subreddit = subreddit,
        isNetworkAvailable = isOnline
    )

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}