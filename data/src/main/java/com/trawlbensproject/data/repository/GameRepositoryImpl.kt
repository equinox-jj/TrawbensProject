package com.trawlbensproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.trawlbens.core.Resource
import com.trawlbensproject.data.datasource.local.dao.GameDao
import com.trawlbensproject.data.datasource.remote.network.ApiService
import com.trawlbensproject.data.datasource.remote.paging.RemotePagingSource
import com.trawlbensproject.data.mapper.toDomain
import com.trawlbensproject.domain.entities.GameDetail
import com.trawlbensproject.domain.entities.ResultItems
import com.trawlbensproject.domain.entities.ScreenshotResponse
import com.trawlbensproject.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class GameRepositoryImpl(
    private val apiService: ApiService,
    private val gameDao: GameDao,
) : GameRepository {

    override fun getGameList(search: String?): Flow<PagingData<ResultItems>> = Pager(
        config = PagingConfig(
            enablePlaceholders = false,
            pageSize = 20,
            initialLoadSize = 20,
        ),
        pagingSourceFactory = {
            RemotePagingSource(
                apiService = apiService,
                search = search,
            )
        }
    ).flow

    override fun getGameById(gameId: Int): Flow<Resource<GameDetail>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getGameById(gameId)

            emit(Resource.Success(response.toDomain()))
        } catch (exception: IOException) {
            emit(Resource.Error(exception.message))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.message))
        }
    }

    override fun getGameScreenshots(gameId: Int): Flow<Resource<ScreenshotResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getGameScreenshot(gameId)

            emit(Resource.Success(response.toDomain()))
        } catch (exception: IOException) {
            emit(Resource.Error(exception.message))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.message))
        }
    }

    override fun getFavoriteGames(): Flow<List<GameDetail>> = gameDao.getFavoriteGames()

    override fun isGameListedInFavorite(gameId: Int?): Boolean =
        gameDao.isGameListedInFavorite(gameId)

    override suspend fun insertFavoriteGame(data: GameDetail) = gameDao.insertFavoriteGame(data)

    override suspend fun deleteFavoriteGame(data: GameDetail) = gameDao.deleteFavoriteGame(data)

    override suspend fun deleteAllFavoriteGames() = gameDao.deleteAllFavoriteGames()
}