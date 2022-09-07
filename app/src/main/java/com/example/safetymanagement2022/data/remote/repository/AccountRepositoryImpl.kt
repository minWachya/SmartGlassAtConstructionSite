package com.example.safetymanagement2022.data.remote.repository

import com.example.safetymanagement2022.data.remote.datasource.AccountDataSource
import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.*
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dataSource: AccountDataSource
) : AccountRepository {
    override suspend fun postAccountRegister(body: RegisterRequest): RegisterResponse =
        dataSource.postAccountRegister(body)
    override suspend fun postAccountLogin(body: LoginRequest): LoginResponse =
        dataSource.postAccountLogin(body)
}