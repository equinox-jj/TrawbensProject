package com.trawlbensproject.presentation.favorite

import androidx.compose.runtime.Immutable
import com.trawlbensproject.domain.entities.GameDetail

@Immutable
data class FavoriteState(
    val isLoading: Boolean = false,
    val favoriteItems: List<GameDetail> = emptyList(),
    val isDropDownExpanded: Boolean = false,
    val isDialogShowed: Boolean = false,
)
