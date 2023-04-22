package com.volokhinaleksey.reddittopics.network

interface ApiHolder {

    val redditApiService: RedditApiService

}

class RedditApiHolder(override val redditApiService: RedditApiService) : ApiHolder