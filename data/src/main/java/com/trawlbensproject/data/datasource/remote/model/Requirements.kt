package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Requirements(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)