package com.trawlbensproject.presentation.di

import com.trawlbensproject.domain.usecase.GameInteractor
import com.trawlbensproject.domain.usecase.GameUseCase
import org.koin.dsl.module

val useCaseDependency = module {
    single<GameUseCase> { GameInteractor(get()) }
}