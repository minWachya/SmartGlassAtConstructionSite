package com.example.safetymanagement2022.repository.list_building

import com.example.safetymanagement2022.model.ListBuildingData

interface ListBuildingDataSource {
    fun getListBuildingData(): ListBuildingData?
}