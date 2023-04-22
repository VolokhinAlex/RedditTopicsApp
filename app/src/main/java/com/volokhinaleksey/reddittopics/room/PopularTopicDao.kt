package com.volokhinaleksey.reddittopics.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.volokhinaleksey.reddittopics.models.local.PopularTopicEntity

/**
 * Interface for interacting with local data on popular topics.
 */

@Dao
interface PopularTopicDao {

    /**
     * Method for adding a new popular topic to the database
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PopularTopicEntity)

    /**
     * Method for updating the topic in the database
     */

    @Update
    suspend fun update(entity: PopularTopicEntity)

    /**
     * Method for deleting a topic from the database
     */

    @Delete
    suspend fun delete(entity: PopularTopicEntity)

    /**
     * Method for getting popular topics by subreddit
     */

    @Query("SELECT * FROM popular_reddit_topic WHERE subreddit LIKE :subreddit ORDER BY title ASC LIMIT :limit OFFSET :offset")
    suspend fun getPopularTopics(limit: Int, offset: Int, subreddit: String): List<PopularTopicEntity>

}