package com.example.safetymanagement2022

import com.example.safetymanagement2022.di.ApiClient

object ServiceLoader {
    private var apiClient: ApiClient? = null

    fun provideApiClient(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

}