package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)