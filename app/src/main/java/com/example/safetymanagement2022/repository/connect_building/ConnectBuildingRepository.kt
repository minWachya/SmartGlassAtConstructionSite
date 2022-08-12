package com.example.safetymanagement2022.repository.connect_building

import com.example.safetymanagement2022.model.ConnectBuildingData

class ConnectBuildingRepository(private val assetDataSource: ConnectBuildingRemoteDataSource) {

    suspend fun getConnectBuildingData(userId: String): ConnectBuildingData {
        return assetDataSource.getConnectBuildingData(userId)
    }
}