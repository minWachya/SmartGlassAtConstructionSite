package com.example.safetymanagement2022.di

import com.example.safetymanagement2022.data.local.LocalPreferencesDataSource
import com.example.safetymanagement2022.data.local.LocalPreferencesDataSourceImpl
import com.example.safetymanagement2022.data.remote.datasource.*
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

    @Binds
    @Singleton
    fun bindsAccountDataSource(dataSourceImpl: AccountDataSourceImpl): AccountDataSource

    @Binds
    @Singleton
    fun bindsSettingDataSource(dataSourceImpl: SettingDataSourceImpl): SettingDataSource

    @Binds
    @Singleton
    fun bindsLocalPreferencesDataSource(localPreferencesDataSource: LocalPreferencesDataSourceImpl): LocalPreferencesDataSource
}