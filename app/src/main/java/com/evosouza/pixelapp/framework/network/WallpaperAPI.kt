package com.evosouza.pixelapp.framework.network

import com.evosouza.pixelapp.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperAPI {
    @GET("v1/curated")
    suspend fun getPopularWallpapers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): DataWrapperResponse
}