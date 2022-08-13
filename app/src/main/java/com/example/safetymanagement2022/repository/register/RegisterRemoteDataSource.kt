package com.example.safetymanagement2022.repository.register

import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import com.example.safetymanagement2022.di.ApiClient

class RegisterRemoteDataSource(private val apiClient: ApiClient) : RegisterDataSource {
    override suspend fun postRegister(body: RegisterRequest): RegisterResponse {
        return apiClient.postRegister(body)
    }
}