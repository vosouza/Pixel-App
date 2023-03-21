package com.evosouza.pixelapp.framework.di

import com.evosouza.core.usecase.popularusecase.GetPopularUseCase
import com.evosouza.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(getPopularUseCaseImpl: GetPopularUseCaseImpl): GetPopularUseCase

}