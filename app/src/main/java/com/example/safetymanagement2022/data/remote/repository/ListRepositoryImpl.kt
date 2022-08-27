package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.datasource.ListDataSource
import com.example.safetymanagement2022.data.remote.model.request.CreateBuildingRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val dataSource: ListDataSource
) : ListRepository {
    override suspend fun getListBuilding(userId: String): ListBuildingResponse = dataSource.getListBuilding(userId)
    override suspend fun getListGlass(userId: String): ListSmartGlassResponse = dataSource.getListGlass(userId)
    override suspend fun postBuildingCreate(
        userId: String,
        body: CreateBuildingRequest,
    ): BuildingCreateResponse = dataSource.postBuildingCreate(userId, body)
    override suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse =
        dataSource.getBuildingDetail(buildingId)
}