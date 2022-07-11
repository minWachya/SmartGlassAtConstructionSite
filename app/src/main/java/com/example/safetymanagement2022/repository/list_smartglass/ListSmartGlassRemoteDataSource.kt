package com.example.safetymanagement2022.repository.list_smartglass

import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.model.ListSmartGlassData
import com.google.gson.Gson

class ListSmartGlassRemoteDataSource(private val assetLoader: AssetLoader): ListSmartGlassDataSource {
    private val gson = Gson()

    override fun getListSmartGlassData(): ListSmartGlassData? {
        return assetLoader.getJsonString("list_smartglass.json").let { listSmartGlassJsonString ->
            gson.fromJson(listSmartGlassJsonString, ListSmartGlassData::class.java)
        }
    }

}