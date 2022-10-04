package com.example.emovie.di

import com.example.emovie.domain.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindTopRatedUseCase( useCase: GetTopRatedMoviesUseCaseImpl) : GetTopRatedMoviesUseCase

    @Binds
    abstract fun bindUpcomingUseCase( useCase: GetUpcomingMoviesUseCaseImpl) : GetUpcomingMoviesUseCase

    @Binds
    abstract fun bindConfigurationUseCase( useCase: GetConfigurationUseCaseImpl) : GetConfigurationUseCase

    @Binds
    abstract fun bindGetDataMoviesUseCase(useCase : GetVideoDataUseCaseImpl) : GetVideoDataUseCase

    @Binds
    abstract fun bindGetRecomendedMoviesUseCase(useCase : GetTopRatedMoviesFilteredUseCaseImpl) : GetTopRatedMoviesFilteredUseCase

}