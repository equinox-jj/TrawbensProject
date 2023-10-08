package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: Platform? = null
)