package com.volokhinaleksey.reddittopics.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.volokhinaleksey.reddittopics.datasource.popular.LocalPopularTopicsDataSource
import com.volokhinaleksey.reddittopics.datasource.popular.PopularTopicsDataSource
import com.volokhinaleksey.reddittopics.models.remote.PopularTopicDTO
import com.volokhinaleksey.reddittopics.models.ui.PopularTopicUI
import com.volokhinaleksey.reddittopics.utils.mapPopularTopicDTOToPopularTopicEntity
import com.volokhinaleksey.reddittopics.utils.mapPopularTopicDTOToPopularTopicUI
import com.volokhinaleksey.reddittopics.utils.mapPopularTopicEntityToPopularTopicUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository Implementation for getting popular topics from some data source.
 */

class PopularTopicsRepositoryImpl(
    private val remoteDataSource: PopularTopicsDataSource<PopularTopicDTO>,
    private val localDataSource: LocalPopularTopicsDataSource
) : PopularTopicsRepository {

    /**
     * The method for getting a list of popular topics from some data source
     * @param subreddit - The group from which you need to get a list of popular topics.
     * @param isNetworkAvailable - The parameter is responsible for which data source will be used to get data, depending on the availability of the network.
     */

    override fun getPopularTopics(
        subreddit: String,
        isNetworkAvailable: Boolean
    ): Flow<PagingData<PopularTopicUI>> {
        return if (isNetworkAvailable) {
            remoteDataSource.getPopularTopics(subreddit = subreddit).map {
                it.map { topic ->
                    localDataSource.savePopularTopic(
                        entity = mapPopularTopicDTOToPopularTopicEntity(
                            popularTopicDTO = topic,
                            subreddit = subreddit
                        )
                    )
                    mapPopularTopicDTOToPopularTopicUI(popularTopicDTO = topic)
                }
            }
        } else {
            localDataSource.getPopularTopics(subreddit = subreddit).map {
                it.map { topic ->
                    mapPopularTopicEntityToPopularTopicUI(entity = topic)
                }
            }
        }
    }

}