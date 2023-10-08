package com.trawlbensproject.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ScreenshotsItems(
    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("is_deleted")
    val isDeleted: Boolean? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("height")
    val height: Int? = null,
)
