package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class YearsItem(

	@field:SerializedName("filter")
	val filter: String? = null,

	@field:SerializedName("nofollow")
	val nofollow: Boolean? = null,

	@field:SerializedName("decade")
	val decade: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("years")
	val years: List<YearsItem?>? = null,

	@field:SerializedName("year")
	val year: Int? = null
)