package com.example.safetymanagement2022.repository.login

import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse

class LoginRepository(private val assetDataSource: LoginRemoteDataSource) {

    suspend fun postLogin(body: LoginRequest): LoginResponse {
        return assetDataSource.postLoginData(body)
    }

}