package com.volokhinaleksey.reddittopics.datasource.popular

import com.volokhinaleksey.reddittopics.models.local.PopularTopicEntity

interface LocalPopularTopicsDataSource : PopularTopicsDataSource<PopularTopicEntity> {

    /**
     * A method for saving a popular topic to a database.
     * @param entity - The topic that needs to be saved
     */

    suspend fun savePopularTopic(entity: PopularTopicEntity)

}