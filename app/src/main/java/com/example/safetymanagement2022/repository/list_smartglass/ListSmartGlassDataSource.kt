package com.example.safetymanagement2022.repository.list_smartglass

import com.example.safetymanagement2022.model.ListSmartGlassData

interface ListSmartGlassDataSource {
    fun getListSmartGlassData(): ListSmartGlassData?
}