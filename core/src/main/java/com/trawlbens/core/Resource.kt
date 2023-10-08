package com.trawlbens.core

sealed interface Resource<out T> {
    data object Loading : Resource<Nothing>
    data class Success<T>(val data: T? = null) : Resource<T>
    data class Error(val message: String? = null) : Resource<Nothing>
}