package com.example.safetymanagement2022.data.remote.service

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import retrofit2.http.*

interface SettingService {
    @GET("accounts/logout/{user_id}")
    suspend fun getAccountLogout(@Path("user_id") userId: String): LogoutResponse
    @PUT("setting/password/{user_id}")
    suspend fun putSettingChangePw(@Path("user_id") userId: String, @Body body: LoginRequest): LoginResponse
    @PUT("setting/user/{user_id}")
    suspend fun putSettingChangeUserIngo(@Path("user_id") userId: String, @Body body: RegisterRequest): RegisterResponse
}