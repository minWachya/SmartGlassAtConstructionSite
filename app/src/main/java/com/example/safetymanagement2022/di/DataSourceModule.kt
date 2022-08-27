package com.example.safetymanagement2022.di

import com.example.safetymanagement2022.data.remote.datasource.HomeDataSource
import com.example.safetymanagement2022.data.remote.datasource.HomeDataSourceImpl
import com.example.safetymanagement2022.data.remote.datasource.ListDataSource
import com.example.safetymanagement2022.data.remote.datasource.ListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindsHomeDataSource(dataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Binds
    @Singleton
    fun bindsListDataSource(dataSourceImpl: ListDataSourceImpl): ListDataSource
}