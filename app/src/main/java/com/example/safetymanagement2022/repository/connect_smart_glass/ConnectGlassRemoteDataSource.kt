package com.example.safetymanagement2022.repository.connect_smart_glass

import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.model.ConnectGlassData
import com.google.gson.Gson

class ConnectGlassRemoteDataSource(private val assetLoader: AssetLoader) : ConnectGlassDataSource {
    private val gson = Gson()

    override fun getConnectGlassData(): ConnectGlassData? {
        return assetLoader.getJsonString("connect_glass.json").let { jsonString ->
            gson.fromJson(jsonString, ConnectGlassData::class.java)
        }
    }

}