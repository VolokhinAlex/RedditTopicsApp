package com.volokhinaleksey.reddittopics.network

import com.volokhinaleksey.reddittopics.models.remote.PopularTopicsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for working with the reddit api
 */

interface RedditApiService {

    /**
     * A method for getting a list of popular topics from reddit by group.
     * @param subreddit - The group for which you need to get a list of popular topics.
     * @param limit - The maximum number of topics to get per request.
     * @param after - The key to open the next page of the group.
     */

    @GET("/r/{subreddit}/hot.json")
    suspend fun getPopularTopics(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int,
        @Query("after") after: String
    ): PopularTopicsDTO

}