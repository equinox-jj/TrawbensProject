package com.trawlbensproject.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trawlbensproject.data.datasource.local.GameTypeConverter
import com.trawlbensproject.data.datasource.local.dao.GameDao
import com.trawlbensproject.domain.entities.GameDetail

@Database(
    entities = [GameDetail::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(GameTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

}