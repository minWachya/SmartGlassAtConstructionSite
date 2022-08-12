package com.example.safetymanagement2022.repository.connect_smart_glass

import com.example.safetymanagement2022.model.ConnectGlassData

class ConnectGlassRepository(private val assetDataSource: ConnectGlassRemoteDataSource) {

    suspend fun getConnectGlassData(userId: String): ConnectGlassData {
        return assetDataSource.getConnectGlassData(userId)
    }
}