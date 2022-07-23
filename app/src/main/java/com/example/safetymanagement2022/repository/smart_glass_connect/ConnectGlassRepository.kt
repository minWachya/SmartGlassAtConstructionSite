package com.example.safetymanagement2022.repository.smart_glass_connect

import com.example.safetymanagement2022.model.ConnectGlassData
class ConnectGlassRepository(private val assetDataSource: ConnectGlassRemoteDataSource) {

    fun getConnectGlassData(): ConnectGlassData? {
        return assetDataSource.getConnectGlassData()
    }
}