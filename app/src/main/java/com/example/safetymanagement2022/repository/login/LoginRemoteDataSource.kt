package com.example.safetymanagement2022.repository.login

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.di.ApiClient

class LoginRemoteDataSource(private val apiClient: ApiClient) : LoginDataSource {
    override suspend fun postLoginData(body: LoginRequest): LoginResponse {
        return apiClient.postLogin(body)
    }
}