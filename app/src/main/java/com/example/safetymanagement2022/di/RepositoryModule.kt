package com.example.safetymanagement2022.di

import com.example.safetymanagement2022.data.remote.repository.*
import com.example.safetymanagement2022.domain.repository.LocalPreferencesRepository
import com.example.safetymanagement2022.domain.repository.LocalPreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    fun bindsListRepository(repository: ListRepositoryImpl): ListRepository

    @Binds
    @Singleton
    fun bindsAccountRepository(repository: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    fun bindsLocalPreferencesRepository(repository: LocalPreferencesRepositoryImpl): LocalPreferencesRepository
}