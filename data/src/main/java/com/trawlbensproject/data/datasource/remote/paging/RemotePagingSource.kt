package com.trawlbensproject.data.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.trawlbensproject.data.datasource.remote.network.ApiService
import com.trawlbensproject.data.mapper.toDomain
import com.trawlbensproject.domain.entities.ResultItems
import okio.IOException

class RemotePagingSource(
    private val apiService: ApiService,
    private val search: String? = null,
) : PagingSource<Int, ResultItems>() {
    override fun getRefreshKey(state: PagingState<Int, ResultItems>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultItems> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getGameList(
                page = position,
                search = search,
            )
            val gameResult = response.results?.mapNotNull { it?.toDomain() }

            val nextKey = if (gameResult?.isEmpty() == true) null else position.plus(1)

            LoadResult.Page(
                data = gameResult ?: emptyList(),
                nextKey = nextKey,
                prevKey = null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}