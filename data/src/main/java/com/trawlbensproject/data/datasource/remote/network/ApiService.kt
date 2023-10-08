package com.trawlbensproject.data.datasource.remote.network

import com.trawlbens.core.utils.Constants
import com.trawlbensproject.data.datasource.remote.model.GameDetailResponse
import com.trawlbensproject.data.datasource.remote.model.GameListResponse
import com.trawlbensproject.data.datasource.remote.model.ScreenshotsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGameList(
        @Query("key") apiKey: String = Constants.API_KEY,
        @Query("page") page: Int = 1,
        @Query("search") search: String? = null,
        @Query("search_exact") searchExact: Boolean = true,
    ): GameListResponse

    @GET("games/{id}")
    suspend fun getGameById(
        @Path("id") gameId: Int,
        @Query("key") apiKey: String = Constants.API_KEY,
    ): GameDetailResponse

    @GET("games/{game_pk}/screenshots")
    suspend fun getGameScreenshot(
        @Path("game_pk") gameId: Int,
        @Query("key") apiKey: String = "8dc0aa73021148589a03b46da9866b42",
    ): ScreenshotsResponse

}