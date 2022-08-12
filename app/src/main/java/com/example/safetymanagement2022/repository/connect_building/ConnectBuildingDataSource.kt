package com.example.safetymanagement2022.repository.connect_building

import com.example.safetymanagement2022.model.ConnectBuildingData

interface ConnectBuildingDataSource {
    suspend fun getConnectBuildingData(userId: String): ConnectBuildingData?
}