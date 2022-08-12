package com.example.safetymanagement2022.repository.home

import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.ConnectIotResponse
import com.example.safetymanagement2022.di.ApiClient
import com.example.safetymanagement2022.model.HomeData

class HomeRemoteDataSource(private val apiClient: ApiClient) : HomeDataSource {
    override suspend fun getHomeData(userId: String): HomeData {
        return apiClient.fetchHome(userId)
    }

    override suspend fun postConnectIot(body: ConnectIotRequest): ConnectIotResponse {
        return apiClient.postConnectIot(body)
    }
}