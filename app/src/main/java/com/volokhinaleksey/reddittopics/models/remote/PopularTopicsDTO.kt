package com.volokhinaleksey.reddittopics.models.remote

import com.google.gson.annotations.SerializedName

data class PopularTopicsDTO(
    val data: ChildrenDTO?,
)

data class ChildrenDTO(
    val after: String?,
    val children: List<ChildrenDataDTO>?,
    val before: String?
)

data class ChildrenDataDTO(
    val data: PopularTopicDTO?
)

/**
 * The object for a popular topic for data from the network.
 *
 * @param subreddit -   The group in which the topic was written
 * @param title -       Topic text
 * @param comments -    The number of comments under the topic
 * @param ups -         The number of votes for who likes/dislikes the topic.
 *
 */

data class PopularTopicDTO(
    val subreddit: String?,
    val title: String?,
    @SerializedName(value = "num_comments")
    val comments: Long?,
    val ups: Long?
)
