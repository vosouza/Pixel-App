package com.evosouza.pixelapp.framework.di

import com.evosouza.core.data.RemoteDataSource
import com.evosouza.pixelapp.framework.network.response.DataWrapperResponse
import com.evosouza.pixelapp.framework.remote.RemotePopularDataSourceImpl
import com.evosouza.core.data.PopularRepository
import com.evosouza.pixelapp.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsPopularRepository(repositoryImpl: PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindsRemoteDataSource(dataSource: RemotePopularDataSourceImpl): RemoteDataSource<DataWrapperResponse>
}