package com.example.safetymanagement2022.repository.register

import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse

interface RegisterDataSource {
    suspend fun postRegister(body: RegisterRequest): RegisterResponse?
}