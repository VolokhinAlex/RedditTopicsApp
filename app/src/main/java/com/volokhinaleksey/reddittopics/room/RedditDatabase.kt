package com.volokhinaleksey.reddittopics.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.volokhinaleksey.reddittopics.models.local.PopularTopicEntity

/**
 * The object for managing the Room database
 */

@Database(
    entities = [PopularTopicEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RedditDatabase : RoomDatabase() {

    abstract fun popularTopicDao(): PopularTopicDao

}