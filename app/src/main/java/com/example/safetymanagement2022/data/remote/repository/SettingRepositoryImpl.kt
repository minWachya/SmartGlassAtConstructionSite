package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.datasource.SettingDataSource
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

    override suspend fun getSettingChangePw(userId: String, body: LoginResponse): LogoutResponse =
        dataSource.getSettingChangePw(userId, body)

    override suspend fun getSettingChangeUserInfo(
        userId: String,
        body: RegisterRequest,
    ): RegisterResponse =
        dataSource.getSettingChangeUserInfo(userId, body)
}