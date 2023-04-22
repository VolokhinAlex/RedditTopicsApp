package com.volokhinaleksey.reddittopics.utils

import com.volokhinaleksey.reddittopics.models.local.PopularTopicEntity
import com.volokhinaleksey.reddittopics.models.remote.PopularTopicDTO
import com.volokhinaleksey.reddittopics.models.ui.PopularTopicUI

fun mapPopularTopicDTOToPopularTopicUI(popularTopicDTO: PopularTopicDTO): PopularTopicUI {
    return PopularTopicUI(
        title = popularTopicDTO.title.orEmpty(),
        comments = popularTopicDTO.comments ?: 0,
        ups = popularTopicDTO.ups ?: 0
    )
}

fun mapPopularTopicEntityToPopularTopicUI(entity: PopularTopicEntity): PopularTopicUI {
    return PopularTopicUI(
        title = entity.title,
        comments = entity.comments ?: 0,
        ups = entity.ups ?: 0
    )
}

fun mapPopularTopicDTOToPopularTopicEntity(
    popularTopicDTO: PopularTopicDTO,
    subreddit: String
): PopularTopicEntity {
    return PopularTopicEntity(
        title = popularTopicDTO.title.orEmpty(),
        comments = popularTopicDTO.comments,
        ups = popularTopicDTO.ups,
        subreddit = subreddit
    )
}