package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import com.example.safetymanagement2022.data.remote.service.HomeService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(private val service: HomeService) : HomeDataSource {
    override suspend fun getHome(userId: String): HomeResponse = service.getHome(userId)
    override suspend fun getConnectGlass(userId: String): ConnectGlassResponse = service.getConnectGlass(userId)
    override suspend fun getConnectBuilding(userId: String): ConnectBuildingResponse = service.getConnectBuilding(userId)
    override suspend fun postConnectIot(body: ConnectIotRequest): ConnectIotResponse = service.postConnectIot(body)
    override suspend fun getDisConnectIot(userId: String): DisConnectResponse = service.getDisConnectIot(userId)
}