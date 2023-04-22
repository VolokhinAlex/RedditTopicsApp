package com.volokhinaleksey.reddittopics.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The essence of the popular topics table for the local database
 *
 * @param title -       Topic text
 * @param comments -    The number of comments under the topic
 * @param ups -         The number of votes for who likes/dislikes the topic
 * @param subreddit -   The group in which the topic was written
 */

@Entity(
    tableName = "popular_reddit_topic"
)
data class PopularTopicEntity(
    @PrimaryKey
    val title: String,
    val comments: Long?,
    val ups: Long?,
    val subreddit: String?
)