package com.example.safetymanagement2022.repository.building_detail

import com.example.safetymanagement2022.model.BuildingDetailData

interface BuildingDetailDataSource {
    fun getBuildingDetailData(): BuildingDetailData?
}