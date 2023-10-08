package com.trawlbensproject.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_list_entity")
data class GameDetail(
    @PrimaryKey
    val id: Int? = 0,
    val gameTitle: String? = "",
    val background: String? = "",
    val description: String? = "",
    val released: String? = "",
    val rating: Double? = 0.0,
    val website: String? = "",
    val parentPlatforms: List<ParentPlatform> = emptyList(),
    val genres: List<GenreItems> = emptyList(),
    val publishers: List<PublisherItems> = emptyList(),
    val metaScore: Int? = 0,
)
