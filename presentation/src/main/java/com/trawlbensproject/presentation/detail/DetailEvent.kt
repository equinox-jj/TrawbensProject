package com.trawlbensproject.presentation.detail

import com.trawlbensproject.domain.entities.GameDetail

sealed interface DetailEvent {
    data object OnGameByIdFetched : DetailEvent
    data object OnGameScreenshotsFetched : DetailEvent
    data class OnFavoriteClicked(val data: GameDetail?) : DetailEvent
    data class OnDeleteFavoriteClicked(val data: GameDetail?) : DetailEvent
    data class OnTextExpandedClicked(val isTextExpanded: Boolean) : DetailEvent
}