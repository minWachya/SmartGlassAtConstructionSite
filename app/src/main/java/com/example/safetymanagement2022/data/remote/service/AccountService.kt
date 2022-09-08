package com.example.safetymanagement2022.data.remote.service

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {
    @POST("accounts/signup/")
    suspend fun postAccountRegister(@Body body: RegisterRequest): RegisterResponse
    @POST("accounts/login/")
    suspend fun postAccountLogin(@Body body: LoginRequest): LoginResponse
}