package com.trawlbensproject.domain.entities

data class ResultItems(
    val id: Int? = 0,
    val gameTitle: String? = "",
    val releaseDate: String? = "",
    val rating: Double? = 0.0,
    val parentPlatforms: List<ParentPlatform> = emptyList(),
    val background: String? = "",
)
