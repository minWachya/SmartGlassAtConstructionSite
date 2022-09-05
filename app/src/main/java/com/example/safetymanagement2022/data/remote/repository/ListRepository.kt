package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate1Request
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate2Request
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.data.remote.model.response.*

interface ListRepository {
    suspend fun getListBuilding(userId: String): ListBuildingResponse
    suspend fun getListGlass(userId: String): ListSmartGlassResponse
    suspend fun postBuildingCreate1(userId: String, body: BuildingCreate1Request): BuildingCreate1Response
    suspend fun postBuildingCreate2(userId: String, body: BuildingCreate2Request): BuildingCreate2Response
    suspend fun postGlassCreate(userId: String, body: GlassCreateRequest): GlassCreateResponse
    suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse
}