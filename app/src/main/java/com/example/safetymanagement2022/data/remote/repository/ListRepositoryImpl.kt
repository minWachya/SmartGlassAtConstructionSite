package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.datasource.ListDataSource
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate1Request
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate2Request
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val dataSource: ListDataSource
) : ListRepository {
    override suspend fun getListBuilding(userId: String): ListBuildingResponse = dataSource.getListBuilding(userId)
    override suspend fun getListGlass(userId: String): ListSmartGlassResponse = dataSource.getListGlass(userId)
    override suspend fun postBuildingCreate1(
        userId: String,
        body: BuildingCreate1Request
    ): BuildingCreate1Response = dataSource.postBuildingCreate1(userId, body)

    override suspend fun postBuildingCreate2(
        userId: String,
        body: BuildingCreate2Request
    ): BuildingCreate2Response = dataSource.postBuildingCreate2(userId, body)
    override suspend fun postGlassCreate(
        userId: String,
        body: GlassCreateRequest,
    ): GlassCreateResponse = dataSource.postGlassCreate(userId, body)
    override suspend fun getBuildingDetail(buildingId: Int): BuildingDetailResponse =
        dataSource.getBuildingDetail(buildingId)
}