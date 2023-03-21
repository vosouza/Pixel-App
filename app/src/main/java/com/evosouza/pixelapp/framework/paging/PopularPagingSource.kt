package com.evosouza.pixelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.evosouza.core.data.RemoteDataSource
import com.evosouza.core.model.PhotoDomain
import com.evosouza.pixelapp.framework.network.response.DataWrapperResponse
import com.evosouza.pixelapp.framework.network.response.toPhotoDomain

class PopularPagingSource(
    private val dataSource: RemoteDataSource<DataWrapperResponse>,
    private val pages: Int
) : PagingSource<Int, PhotoDomain>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDomain> {
        return try {
            val nextPage = params.key ?: PAGE_INDEX
            val popularResponse = dataSource.fetchPopular(page = nextPage, perPage = pages)
            LoadResult.Page(
                data = popularResponse.photos.map { it.toPhotoDomain() },
                prevKey = null,
                nextKey = if (popularResponse.page >= nextPage) nextPage + PAGE_INDEX else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val PAGE_INDEX = 1
    }
}