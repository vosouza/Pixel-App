package com.evosouza.pixelapp.framework.remote

import com.evosouza.core.data.RemoteDataSource
import com.evosouza.pixelapp.framework.network.WallpaperAPI
import com.evosouza.pixelapp.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class RemotePopularDataSourceImpl @Inject constructor(
    private val api: WallpaperAPI
): RemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchPopular(page: Int, perPage: Int): DataWrapperResponse =
        api.getPopularWallpapers(page, perPage)
}