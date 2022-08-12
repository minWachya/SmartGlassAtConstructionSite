package com.example.safetymanagement2022.repository.connect_building

import com.example.safetymanagement2022.di.ApiClient
import com.example.safetymanagement2022.model.ConnectBuildingData

class ConnectBuildingRemoteDataSource(private val apiClient: ApiClient) : ConnectBuildingDataSource {

    override suspend fun getConnectBuildingData(userId: String): ConnectBuildingData {
        return apiClient.fetchConnectBuilding(userId)
    }

}