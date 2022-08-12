package com.example.safetymanagement2022.repository.connect_smart_glass

import com.example.safetymanagement2022.di.ApiClient
import com.example.safetymanagement2022.model.ConnectGlassData

class ConnectGlassRemoteDataSource(private val apiClient: ApiClient) : ConnectGlassDataSource {

    override suspend fun getConnectGlassData(userId: String): ConnectGlassData {
        return apiClient.fetchConnectGlass(userId)
    }

}