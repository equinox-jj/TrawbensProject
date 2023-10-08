package com.trawlbensproject.presentation.di

import com.trawlbensproject.presentation.detail.DetailViewModel
import com.trawlbensproject.presentation.favorite.FavoriteViewModel
import com.trawlbensproject.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelDependency = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}