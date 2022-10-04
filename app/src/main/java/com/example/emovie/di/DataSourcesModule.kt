package com.example.emovie.di

import com.example.emovie.data.local.LocalDataSource
import com.example.emovie.data.local.LocalDataSourceImpl
import com.example.emovie.data.network.RemoteDataSource
import com.example.emovie.data.network.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl) : RemoteDataSource

}