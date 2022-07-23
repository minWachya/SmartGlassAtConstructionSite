package com.example.safetymanagement2022.repository.connect_building

import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.model.ConnectBuildingData
import com.google.gson.Gson

class ConnectBuildingRemoteDataSource(private val assetLoader: AssetLoader) :
    ConnectBuildingDataSource {
    private val gson = Gson()

    override fun getConnectBuildingData(): ConnectBuildingData? {
        return assetLoader.getJsonString("connect_building.json").let { jsonString ->
            gson.fromJson(jsonString, ConnectBuildingData::class.java)
        }
    }

}