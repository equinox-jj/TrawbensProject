package com.trawlbensproject.presentation.favorite

import com.trawlbensproject.domain.entities.GameDetail

sealed interface FavoriteEvent {
    data object ShowFavoriteGameList : FavoriteEvent
    data class OnDeleteAllClicked(val showDialog: Boolean) : FavoriteEvent
    data object OnDeleteAllDialogClicked: FavoriteEvent
    data class OnDropdownMenuClicked(val expanded: Boolean) : FavoriteEvent
    data class OnDeleteSingleItemClicked(val data: GameDetail) : FavoriteEvent

}