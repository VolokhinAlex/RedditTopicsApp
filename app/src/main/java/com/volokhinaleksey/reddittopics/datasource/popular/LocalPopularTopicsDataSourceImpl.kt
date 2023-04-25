package com.volokhinaleksey.reddittopics.datasource.popular

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.volokhinaleksey.reddittopics.datasource.popular.pagesource.LocalPopularTopicsPagingDataSource
import com.volokhinaleksey.reddittopics.models.local.PopularTopicEntity
import com.volokhinaleksey.reddittopics.room.RedditDatabase
import kotlinx.coroutines.flow.Flow

class LocalPopularTopicsDataSourceImpl(
    private val db: RedditDatabase
) : LocalPopularTopicsDataSource {

    /**
     * A method for saving a popular topic to a database.
     * @param entity - The topic that needs to be saved
     */

    override suspend fun savePopularTopic(entity: PopularTopicEntity) {
        db.popularTopicDao().insert(entity = entity)
    }

    /**
     * Method for getting popular topics from database
     * @param subreddit - The group from which you want to get posts
     */

    override fun getPopularTopics(subreddit: String): Flow<PagingData<PopularTopicEntity>> {
        return Pager(PagingConfig(
            pageSize = PopularTopicsDataSource.PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = PopularTopicsDataSource.INITIAL_LOAD_SIZE
        ), pagingSourceFactory = {
            LocalPopularTopicsPagingDataSource(
                dao = db.popularTopicDao(),
                subreddit = subreddit
            )
        }).flow
    }

}