package com.volokhinaleksey.reddittopics.models.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * The object for a popular topic for UI
 *
 * @param title -       Topic text
 * @param comments -    The number of comments under the topic
 * @param ups -         The number of votes for who likes/dislikes the topic
 */

@Parcelize
data class PopularTopicUI(
    val title: String = "",
    val comments: Long = 0,
    val ups: Long = 0
) : Parcelable