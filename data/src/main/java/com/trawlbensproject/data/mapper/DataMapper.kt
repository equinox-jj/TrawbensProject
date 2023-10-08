package com.trawlbensproject.data.mapper

import com.trawlbensproject.data.datasource.remote.model.GameDetailResponse
import com.trawlbensproject.data.datasource.remote.model.GameListResponse
import com.trawlbensproject.data.datasource.remote.model.GenresItem
import com.trawlbensproject.data.datasource.remote.model.ParentPlatformsItem
import com.trawlbensproject.data.datasource.remote.model.Platform
import com.trawlbensproject.data.datasource.remote.model.PublishersItem
import com.trawlbensproject.data.datasource.remote.model.ResultsItem
import com.trawlbensproject.data.datasource.remote.model.ScreenshotsItems
import com.trawlbensproject.data.datasource.remote.model.ScreenshotsResponse
import com.trawlbensproject.domain.entities.GameDetail
import com.trawlbensproject.domain.entities.GameList
import com.trawlbensproject.domain.entities.GenreItems
import com.trawlbensproject.domain.entities.ParentPlatform
import com.trawlbensproject.domain.entities.PlatformItems
import com.trawlbensproject.domain.entities.PublisherItems
import com.trawlbensproject.domain.entities.ResultItems
import com.trawlbensproject.domain.entities.ScreenshotItems
import com.trawlbensproject.domain.entities.ScreenshotResponse

fun GameListResponse.toDomain() = GameList(
    resultItem = results?.mapNotNull { it?.toDomain() } ?: emptyList()
)

fun ResultsItem.toDomain() = ResultItems(
    id = id,
    gameTitle = name,
    releaseDate = released,
    rating = rating.toString().toDouble(),
    parentPlatforms = parentPlatforms?.mapNotNull { it?.toDomain() } ?: emptyList(),
    background = backgroundImage
)

fun GenresItem.toDomain() = GenreItems(
    id = id,
    genreName = name,
)

fun GameDetailResponse.toDomain() = GameDetail(
    id = id,
    gameTitle = name,
    background = backgroundImage,
    description = description,
    released = released,
    rating = rating.toString().toDouble(),
    website = website,
    parentPlatforms = parentPlatforms?.mapNotNull { it?.toDomain() } ?: emptyList(),
    genres = genres?.mapNotNull { it?.toDomain() } ?: emptyList(),
    publishers = publishers?.mapNotNull { it?.toDomain() } ?: emptyList(),
    metaScore = metacritic,
)

fun ParentPlatformsItem.toDomain() = ParentPlatform(
    platform = platform?.toDomain()
)

fun Platform.toDomain() = PlatformItems(
    id = id,
    name = name,
)

fun PublishersItem.toDomain() = PublisherItems(
    name = name,
    id = id,
)

fun ScreenshotsResponse.toDomain() = ScreenshotResponse(
    results = results?.mapNotNull { it?.toDomain() } ?: emptyList()
)

fun ScreenshotsItems.toDomain() = ScreenshotItems(
    image = image,
    width = width,
    id = id,
    height = height,
)