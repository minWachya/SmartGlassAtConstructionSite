package com.example.safetymanagement2022.data.remote.datasource

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import com.example.safetymanagement2022.data.remote.service.AccountService
import javax.inject.Inject

class AccountDataSourceImpl @Inject constructor(private val service: AccountService) : AccountDataSource  {
    override suspend fun postAccountRegister(body: RegisterRequest): RegisterResponse = service.postAccountRegister(body)
    override suspend fun postAccountLogin(body: LoginRequest): LoginResponse = service.postAccountLogin(body)
}