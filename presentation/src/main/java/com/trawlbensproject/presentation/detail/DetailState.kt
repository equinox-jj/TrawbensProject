package com.trawlbensproject.presentation.detail

import androidx.compose.runtime.Immutable
import com.trawlbensproject.domain.entities.GameDetail
import com.trawlbensproject.domain.entities.ScreenshotItems

@Immutable
data class DetailState(
    val isLoading: Boolean = false,
    val gameDetail: GameDetail? = null,
    val gameScreenShots: List<ScreenshotItems> = emptyList(),
    val errorMessage: String? = null,
    val isTextExpanded: Boolean = false,
    val isGameSaved: Boolean = false,
)