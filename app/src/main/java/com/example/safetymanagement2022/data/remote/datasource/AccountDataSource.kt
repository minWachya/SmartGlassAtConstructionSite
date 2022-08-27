package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.*

interface AccountDataSource {
    suspend fun postAccountRegister(body: RegisterRequest): RegisterResponse
    suspend fun postAccountLogin(body: LoginRequest): LoginResponse
    suspend fun getAccountLogout(userId: String): LogoutResponse
}