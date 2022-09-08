package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse

interface SettingDataSource {
    suspend fun getAccountLogout(userId: String): LogoutResponse
    suspend fun getSettingChangePw(userId: String, body: LoginResponse): LogoutResponse
    suspend fun getSettingChangeUserInfo(userId: String, body: RegisterRequest): RegisterResponse
}