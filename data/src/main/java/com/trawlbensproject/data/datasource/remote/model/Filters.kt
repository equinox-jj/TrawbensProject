package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Filters(

	@field:SerializedName("years")
	val years: List<YearsItem?>? = null
)