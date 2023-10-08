package com.trawlbensproject.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trawlbensproject.domain.entities.GenreItems
import com.trawlbensproject.domain.entities.ParentPlatform
import com.trawlbensproject.domain.entities.PublisherItems

class GameTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun parentPlatformToString(data: List<ParentPlatform>?): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun stringToParentPlatform(data: String): List<ParentPlatform>? {
        val listType = object : TypeToken<List<ParentPlatform>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun genresToString(data: List<GenreItems>?): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun stringToGenres(data: String): List<GenreItems>? {
        val listType = object : TypeToken<List<GenreItems>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun publisherItemsToString(data: List<PublisherItems>?): String {
        return gson.toJson(data)
    }

    @TypeConverter
    fun stringToPublisherItems(data: String): List<PublisherItems>? {
        val listType = object : TypeToken<List<PublisherItems>?>() {}.type
        return gson.fromJson(data, listType)
    }
}