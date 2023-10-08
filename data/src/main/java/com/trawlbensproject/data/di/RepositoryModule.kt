package com.trawlbensproject.data.di

import com.trawlbensproject.data.repository.GameRepositoryImpl
import com.trawlbensproject.domain.repository.GameRepository
import org.koin.dsl.module

val repositoryDependency = module {
    single<GameRepository> { GameRepositoryImpl(get(), get()) }
}