package com.example.safetymanagement2022.data.remote.service

import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SettingService {
    @GET("accounts/logout/{user_id}")
    suspend fun getAccountLogout(@Path("user_id") userId: String): LogoutResponse
    @POST("setting/password/{user_id}")
    suspend fun getSettingChangePw(@Path("user_id") userId: String, @Body body: LoginResponse): LogoutResponse
    @POST("setting/user/{user_id}")
    suspend fun getSettingChangeUserIngo(@Path("user_id") userId: String, @Body body: RegisterRequest): RegisterResponse
}