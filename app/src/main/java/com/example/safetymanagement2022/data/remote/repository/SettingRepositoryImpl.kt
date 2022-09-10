package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.datasource.SettingDataSource
import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import javax.inject.Inject

class SettingRepositoryImpl  @Inject constructor(
    private val dataSource: SettingDataSource
) : SettingRepository {
    override suspend fun getAccountLogout(userId: String): LogoutResponse =
        dataSource.getAccountLogout(userId)

    override suspend fun putSettingChangePw(userId: String, body: LoginRequest): LoginResponse =
        dataSource.putSettingChangePw(userId, body)

    override suspend fun putSettingChangeUserInfo(
        userId: String,
        body: RegisterRequest,
    ): RegisterResponse =
        dataSource.putSettingChangeUserInfo(userId, body)
}