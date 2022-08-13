package com.example.safetymanagement2022.repository.register

import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse

class RegisterRepository(private val assetDataSource: RegisterRemoteDataSource) {

    suspend fun postRegister(body: RegisterRequest): RegisterResponse {
        return assetDataSource.postRegister(body)
    }

}