package com.trawlbensproject.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trawlbensproject.domain.entities.GameDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertFavoriteGame(data: GameDetail)

    @Query("SELECT * FROM game_list_entity")
    fun getFavoriteGames(): Flow<List<GameDetail>>

    @Delete
    suspend fun deleteFavoriteGame(data: GameDetail)

    @Query("DELETE FROM game_list_entity")
    suspend fun deleteAllFavoriteGames()

    @Query("SELECT * FROM game_list_entity WHERE id is :gameId")
    fun isGameListedInFavorite(gameId: Int?): Boolean
}