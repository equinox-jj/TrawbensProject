package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(

	@field:SerializedName("added")
	val added: Int? = null,

	@field:SerializedName("developers")
	val developers: List<DevelopersItem?>? = null,

	@field:SerializedName("name_original")
	val nameOriginal: String? = null,

	@field:SerializedName("rating")
	val rating: Any? = null,

	@field:SerializedName("game_series_count")
	val gameSeriesCount: Int? = null,

	@field:SerializedName("playtime")
	val playtime: Int? = null,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem?>? = null,

	@field:SerializedName("rating_top")
	val ratingTop: Int? = null,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: Int? = null,

	@field:SerializedName("publishers")
	val publishers: List<PublishersItem?>? = null,

	@field:SerializedName("achievements_count")
	val achievementsCount: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("parent_platforms")
	val parentPlatforms: List<ParentPlatformsItem?>? = null,

	@field:SerializedName("reddit_name")
	val redditName: String? = null,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("youtube_count")
	val youtubeCount: Int? = null,

	@field:SerializedName("movies_count")
	val moviesCount: Int? = null,

	@field:SerializedName("description_raw")
	val descriptionRaw: String? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("tba")
	val tba: Boolean? = null,

	@field:SerializedName("dominant_color")
	val dominantColor: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("reddit_description")
	val redditDescription: String? = null,

	@field:SerializedName("reddit_logo")
	val redditLogo: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null,

	@field:SerializedName("reviews_count")
	val reviewsCount: Int? = null,

	@field:SerializedName("metacritic")
	val metacritic: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("metacritic_url")
	val metacriticUrl: String? = null,

	@field:SerializedName("alternative_names")
	val alternativeNames: List<String?>? = null,

	@field:SerializedName("parents_count")
	val parentsCount: Int? = null,

	@field:SerializedName("user_game")
	val userGame: Any? = null,

	@field:SerializedName("metacritic_platforms")
	val metacriticPlatforms: List<MetacriticPlatformsItem?>? = null,

	@field:SerializedName("creators_count")
	val creatorsCount: Int? = null,

	@field:SerializedName("ratings")
	val ratings: List<RatingsItem?>? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("saturated_color")
	val saturatedColor: String? = null,

	@field:SerializedName("added_by_status")
	val addedByStatus: AddedByStatus? = null,

	@field:SerializedName("reddit_url")
	val redditUrl: String? = null,

	@field:SerializedName("reddit_count")
	val redditCount: Int? = null,

	@field:SerializedName("parent_achievements_count")
	val parentAchievementsCount: Int? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int? = null,

	@field:SerializedName("stores")
	val stores: List<StoresItem?>? = null,

	@field:SerializedName("additions_count")
	val additionsCount: Int? = null,

	@field:SerializedName("twitch_count")
	val twitchCount: Int? = null,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String? = null,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating? = null,

	@field:SerializedName("screenshots_count")
	val screenshotsCount: Int? = null,

	@field:SerializedName("reactions")
	val reactions: Reactions? = null,

	@field:SerializedName("clip")
	val clip: Any? = null
)