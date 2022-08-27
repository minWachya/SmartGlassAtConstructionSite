package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.CreateBuildingRequest
import com.example.safetymanagement2022.data.remote.model.response.BuildingCreateResponse
import com.example.safetymanagement2022.data.remote.model.response.BuildingDetailResponse
import com.example.safetymanagement2022.data.remote.model.response.ListBuildingResponse
import com.example.safetymanagement2022.data.remote.model.response.ListSmartGlassResponse

interface ListDataSource {
    suspend fun getListBuilding(userId: String): ListBuildingResponse
    suspend fun getListGlass(userId: String): ListSmartGlassResponse
    suspend fun postBuildingCreate(userId: String, body: CreateBuildingRequest): BuildingCreateResponse
    suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse
}