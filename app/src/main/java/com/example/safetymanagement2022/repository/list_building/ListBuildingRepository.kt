package com.example.safetymanagement2022.repository.list_building

import com.example.safetymanagement2022.model.ListBuildingData

class ListBuildingRepository(private val assetDataSource: ListBuildingRemoteDataSource) {

    fun getListBuildingData(): ListBuildingData? {
        return assetDataSource.getListBuildingData()
    }
}