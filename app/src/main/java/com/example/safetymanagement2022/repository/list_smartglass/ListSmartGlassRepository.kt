package com.example.safetymanagement2022.repository.list_smartglass

import com.example.safetymanagement2022.model.ListSmartGlassData

class ListSmartGlassRepository(private val assetDataSource: ListSmartGlassRemoteDataSource) {
    fun getListSmartGlassData(): ListSmartGlassData? {
        return assetDataSource.getListSmartGlassData()
    }
}