package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class StoresItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("store")
	val store: Store? = null,

	@field:SerializedName("url")
	val url: String? = null
)