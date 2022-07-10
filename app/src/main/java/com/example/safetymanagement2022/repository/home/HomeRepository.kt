package com.example.safetymanagement2022.repository.home

import com.example.safetymanagement2022.model.HomeData

class HomeRepository(private val assetDataSource: HomeRemoteDataSource) {

    fun getHomeData(): HomeData? {
        val arr = assetDataSource.getHomeData()
        return arr
    }
}