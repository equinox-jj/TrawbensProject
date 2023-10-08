package com.trawlbensproject.domain.usecase

import androidx.paging.PagingData
import com.trawlbens.core.Resource
import com.trawlbensproject.domain.entities.GameDetail
import com.trawlbensproject.domain.entities.ResultItems
import com.trawlbensproject.domain.entities.ScreenshotResponse
import com.trawlbensproject.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(
    private val gameRepository: GameRepository,
) : GameUseCase {
    override fun getGameList(search: String?): Flow<PagingData<ResultItems>> {
        return gameRepository.getGameList(search)
    }

    override fun getGameById(gameId: Int): Flow<Resource<GameDetail>> {
        return gameRepository.getGameById(gameId)
    }

    override fun getGameScreenshots(gameId: Int): Flow<Resource<ScreenshotResponse>> {
        return gameRepository.getGameScreenshots(gameId)
    }

    override fun getFavoriteGames(): Flow<List<GameDetail>> {
        return gameRepository.getFavoriteGames()
    }

    override fun isGameListedInFavorite(gameId: Int?): Boolean {
        return gameRepository.isGameListedInFavorite(gameId)
    }

    override suspend fun insertFavoriteGame(data: GameDetail) {
        return gameRepository.insertFavoriteGame(data)
    }

    override suspend fun deleteFavoriteGame(data: GameDetail) {
        return gameRepository.deleteFavoriteGame(data)
    }

    override suspend fun deleteAllFavoriteGames() {
        return gameRepository.deleteAllFavoriteGames()
    }

}