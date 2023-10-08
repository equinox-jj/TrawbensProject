package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ScreenshotsResponse(
    @field:SerializedName("next")
    val next: Any? = null,

    @field:SerializedName("previous")
    val previous: Any? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("results")
    val results: List<ScreenshotsItems?>? = null,
)
