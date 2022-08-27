package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.CreateBuildingRequest
import com.example.safetymanagement2022.data.remote.model.response.BuildingCreateResponse
import com.example.safetymanagement2022.data.remote.model.response.BuildingDetailResponse
import com.example.safetymanagement2022.data.remote.model.response.ListBuildingResponse
import com.example.safetymanagement2022.data.remote.model.response.ListSmartGlassResponse
import com.example.safetymanagement2022.data.remote.service.ListService
import javax.inject.Inject

class ListDataSourceImpl @Inject constructor(private val service: ListService) : ListDataSource {
    override suspend fun getListBuilding(userId: String): ListBuildingResponse = service.getListBuilding(userId)
    override suspend fun getListGlass(userId: String): ListSmartGlassResponse = service.getListGlass(userId)
    override suspend fun postBuildingCreate(
        userId: String,
        body: CreateBuildingRequest,
    ): BuildingCreateResponse = service.postBuildingCreate(userId, body)
    override suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse
    = service.getBuildingDetail(buildingId)
}