package com.example.safetymanagement2022.repository.home

import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.model.HomeData
import com.google.gson.Gson

class HomeRemoteDataSource(private val assetLoader: AssetLoader) : HomeDataSource {
    private val gson = Gson()

    override fun getHomeData(): HomeData? {
        return assetLoader.getJsonString("home.json").let { homeJsonString ->
            gson.fromJson(homeJsonString, HomeData::class.java)
        }
    }
}