package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse

interface SettingDataSource {
    suspend fun getAccountLogout(userId: String): LogoutResponse
    suspend fun putSettingChangePw(userId: String, body: LoginRequest): LoginResponse
    suspend fun putSettingChangeUserInfo(userId: String, body: RegisterRequest): RegisterResponse
}