package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate1Request
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate2Request
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import com.example.safetymanagement2022.data.remote.service.ListService
import javax.inject.Inject

class ListDataSourceImpl @Inject constructor(private val service: ListService) : ListDataSource {
    override suspend fun getListBuilding(userId: String): ListBuildingResponse = service.getListBuilding(userId)
    override suspend fun getListGlass(userId: String): ListSmartGlassResponse = service.getListGlass(userId)
    override suspend fun postBuildingCreate1(
        userId: String,
        body: BuildingCreate1Request
    ): BuildingCreate1Response = service.postBuildingCreate1(userId, body)
    override suspend fun postBuildingCreate2(
        userId: String,
        body: BuildingCreate2Request
    ): BuildingCreate2Response = service.postBuildingCreate2(userId, body)
    override suspend fun postGlassCreate(
        userId: String,
        body: GlassCreateRequest,
    ): GlassCreateResponse = service.postGlassCreate(userId, body)
    override suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse
    = service.getBuildingDetail(buildingId)
}