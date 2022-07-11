package com.example.safetymanagement2022.repository.list_building

import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.model.ListBuildingData
import com.google.gson.Gson

class ListBuildingRemoteDataSource(private val assetLoader: AssetLoader) : ListBuildingDataSource {
    private val gson = Gson()

    override fun getListBuildingData(): ListBuildingData? {
        return assetLoader.getJsonString("list_building.json").let { listBuildingJsonString ->
            gson.fromJson(listBuildingJsonString, ListBuildingData::class.java)
        }
    }
}