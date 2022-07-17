package com.example.safetymanagement2022.repository.building_detail

import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.model.BuildingDetailData
import com.google.gson.Gson

class BuildingDetailRemoteDataSource(private val assetLoader: AssetLoader) : BuildingDetailDataSource {
    private val gson = Gson()

    override fun getBuildingDetailData(): BuildingDetailData? {
        return assetLoader.getJsonString("building_detail.json").let { buildingDetailJsonString ->
            gson.fromJson(buildingDetailJsonString, BuildingDetailData::class.java)
        }
    }
}