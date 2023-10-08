package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class EsrbRating(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)