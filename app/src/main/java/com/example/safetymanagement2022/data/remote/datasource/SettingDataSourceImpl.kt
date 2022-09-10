package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import com.example.safetymanagement2022.data.remote.service.SettingService
import javax.inject.Inject

class SettingDataSourceImpl @Inject constructor(private val service: SettingService) : SettingDataSource {
    override suspend fun getAccountLogout(userId: String): LogoutResponse =
        service.getAccountLogout(userId)
    override suspend fun putSettingChangePw(userId: String, body: LoginRequest): LoginResponse =
        service.putSettingChangePw(userId, body)
    override suspend fun putSettingChangeUserInfo(userId: String, body: RegisterRequest): RegisterResponse =
        service.putSettingChangeUserIngo(userId, body)
}