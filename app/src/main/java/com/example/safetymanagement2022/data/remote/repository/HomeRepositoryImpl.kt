package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.datasource.HomeDataSource
import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHome(userId: String): HomeResponse = dataSource.getHome(userId)
    override suspend fun getConnectGlass(userId: String): ConnectGlassResponse = dataSource.getConnectGlass(userId)
    override suspend fun getConnectBuilding(userId: String): ConnectBuildingResponse = dataSource.getConnectBuilding(userId)
    override suspend fun postConnectIot(body: ConnectIotRequest): ConnectIotResponse = dataSource.postConnectIot(body)
    override suspend fun getDisConnectIot(userId: String): DisConnectResponse = dataSource.getDisConnectIot(userId)
}