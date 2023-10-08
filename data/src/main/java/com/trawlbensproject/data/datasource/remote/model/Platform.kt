package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Platform(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("year_end")
	val yearEnd: Any? = null,

	@field:SerializedName("year_start")
	val yearStart: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("platform")
	val platform: Int? = null
)