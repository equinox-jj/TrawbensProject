package com.trawlbensproject.domain.usecase

import androidx.paging.PagingData
import com.trawlbens.core.Resource
import com.trawlbensproject.domain.entities.GameDetail
import com.trawlbensproject.domain.entities.ResultItems
import com.trawlbensproject.domain.entities.ScreenshotResponse
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGameList(search: String? = null): Flow<PagingData<ResultItems>>
    fun getGameById(gameId: Int): Flow<Resource<GameDetail>>
    fun getGameScreenshots(gameId: Int): Flow<Resource<ScreenshotResponse>>
    fun getFavoriteGames(): Flow<List<GameDetail>>
    fun isGameListedInFavorite(gameId: Int?): Boolean
    suspend fun insertFavoriteGame(data: GameDetail)
    suspend fun deleteFavoriteGame(data: GameDetail)
    suspend fun deleteAllFavoriteGames()
}