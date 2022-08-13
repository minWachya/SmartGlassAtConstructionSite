package com.example.safetymanagement2022.repository.home

import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.ConnectIotResponse
import com.example.safetymanagement2022.data.remote.model.response.DisConnectResponse
import com.example.safetymanagement2022.model.HomeData

class HomeRepository(private val assetDataSource: HomeRemoteDataSource) {
    suspend fun fetchHomeData(userId: String): HomeData {
        return assetDataSource.getHomeData(userId)
    }

    suspend fun postConnectIot(body: ConnectIotRequest): ConnectIotResponse {
        return assetDataSource.postConnectIot(body)
    }

    suspend fun fetchDisConnectIot(userId: String): DisConnectResponse {
        return assetDataSource.fetchDisConnectIot(userId)
    }

}