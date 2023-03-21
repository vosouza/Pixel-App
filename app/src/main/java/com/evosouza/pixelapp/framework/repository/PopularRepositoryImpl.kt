package com.evosouza.pixelapp.framework.repository

import androidx.paging.PagingSource
import com.evosouza.core.data.PopularRepository
import com.evosouza.core.data.RemoteDataSource
import com.evosouza.core.model.PhotoDomain
import com.evosouza.pixelapp.framework.network.response.DataWrapperResponse
import com.evosouza.pixelapp.framework.paging.PopularPagingSource

class PopularRepositoryImpl(
    private val remoteDataSource: RemoteDataSource<DataWrapperResponse>
): PopularRepository {
    override fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain> {
        return PopularPagingSource(remoteDataSource, pages)
    }
}