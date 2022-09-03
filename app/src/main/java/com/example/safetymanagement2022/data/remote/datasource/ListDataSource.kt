package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.CreateBuildingRequest
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.data.remote.model.response.*

interface ListDataSource {
    suspend fun getListBuilding(userId: String): ListBuildingResponse
    suspend fun getListGlass(userId: String): ListSmartGlassResponse
    suspend fun postBuildingCreate(userId: String, body: CreateBuildingRequest): BuildingCreateResponse
    suspend fun postGlassCreate(userId: String, body: GlassCreateRequest): GlassCreateResponse
    suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse
}