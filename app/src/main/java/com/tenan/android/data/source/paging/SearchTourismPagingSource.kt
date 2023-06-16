package com.tenan.android.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tenan.android.data.source.remote.TourismService
import com.tenan.android.entity.Tourism

class SearchTourismPagingSource(
    private val query: String?,
    private val city: String?,
    private val tourismService: TourismService,
): PagingSource<Int, Tourism>() {

    override fun getRefreshKey(state: PagingState<Int, Tourism>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tourism> {
        return try {
            val page = params.key ?: 1
            val response = tourismService.searchTourism(
                query = query,
                city = city,
                page = page
            )
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}