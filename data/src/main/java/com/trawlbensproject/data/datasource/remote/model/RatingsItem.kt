package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class RatingsItem(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("percent")
	val percent: Any? = null
)