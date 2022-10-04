package com.example.emovie.di

import com.example.emovie.data.repository.HomeRepository
import com.example.emovie.data.repository.HomeRepositoryImpl
import com.example.emovie.data.repository.ShowVideoRepository
import com.example.emovie.data.repository.ShowVideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl) : HomeRepository

    @Binds
    abstract fun bindsShowVideoRepository(showVideoRepository: ShowVideoRepositoryImpl) : ShowVideoRepository

}