package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class AddedByStatus(

	@field:SerializedName("owned")
	val owned: Int? = null,

	@field:SerializedName("beaten")
	val beaten: Int? = null,

	@field:SerializedName("dropped")
	val dropped: Int? = null,

	@field:SerializedName("yet")
	val yet: Int? = null,

	@field:SerializedName("playing")
	val playing: Int? = null,

	@field:SerializedName("toplay")
	val toplay: Int? = null
)