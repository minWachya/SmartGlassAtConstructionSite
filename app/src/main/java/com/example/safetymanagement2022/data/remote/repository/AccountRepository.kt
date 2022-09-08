package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.*

interface AccountRepository {
    suspend fun postAccountRegister(body: RegisterRequest): RegisterResponse
    suspend fun postAccountLogin(body: LoginRequest): LoginResponse
}