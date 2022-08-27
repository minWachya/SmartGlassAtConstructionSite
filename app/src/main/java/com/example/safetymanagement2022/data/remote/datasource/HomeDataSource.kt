package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.*

interface HomeDataSource {
    suspend fun getHome(userId: String): HomeResponse
    suspend fun getConnectGlass(userId: String): ConnectGlassResponse
    suspend fun getConnectBuilding(userId: String): ConnectBuildingResponse
    suspend fun postConnectIot(body: ConnectIotRequest): ConnectIotResponse
    suspend fun getDisConnectIot(userId: String): DisConnectResponse
}