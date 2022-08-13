package com.example.safetymanagement2022.repository.login

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse

interface LoginDataSource {
    suspend fun postLoginData(body: LoginRequest): LoginResponse?
}