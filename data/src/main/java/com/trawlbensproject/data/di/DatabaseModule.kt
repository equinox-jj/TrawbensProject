package com.trawlbensproject.data.di

import android.content.Context
import androidx.room.Room
import com.trawlbensproject.data.datasource.local.database.GameDatabase
import org.koin.dsl.module

val databaseDependency = module {
    single { provideGameDatabase(get()) }
    single { provideDao(get()) }
}

private fun provideGameDatabase(context: Context) = Room.databaseBuilder(
    context,
    GameDatabase::class.java,
    "game_database"
).allowMainThreadQueries().build()

private fun provideDao(db: GameDatabase) = db.gameDao()