package com.example.safetymanagement2022.repository.building_detail

import com.example.safetymanagement2022.model.BuildingDetailData
class BuildingDetailRepository(private val assetDataSource: BuildingDetailRemoteDataSource) {

    fun getBuildingDetailData(): BuildingDetailData? {
        return assetDataSource.getBuildingDetailData()
    }
}