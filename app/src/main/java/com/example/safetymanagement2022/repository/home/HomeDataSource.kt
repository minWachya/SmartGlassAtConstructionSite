package com.example.safetymanagement2022.repository.home

import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.ConnectIotResponse
import com.example.safetymanagement2022.data.remote.model.response.DisConnectResponse
import com.example.safetymanagement2022.model.HomeData

interface HomeDataSource {
    suspend fun getHomeData(userId: String): HomeData?
    suspend fun postConnectIot(body: ConnectIotRequest): ConnectIotResponse?
    suspend fun fetchDisConnectIot(userId: String): DisConnectResponse?
}