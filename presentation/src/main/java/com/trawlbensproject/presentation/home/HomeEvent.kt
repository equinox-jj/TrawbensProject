package com.trawlbensproject.presentation.home

sealed interface HomeEvent {
    data object OnGamePagingFetched : HomeEvent
    data class OnDropdownMenuClicked(val expanded: Boolean) : HomeEvent
    data class OnSearchQueryTextChanged(val query: String) : HomeEvent
}