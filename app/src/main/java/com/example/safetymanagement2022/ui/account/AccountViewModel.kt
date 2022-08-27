package com.example.safetymanagement2022.ui.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.request.LoginRequest
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.LoginResponse
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import com.example.safetymanagement2022.data.remote.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository: AccountRepository
): ViewModel() {
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _logoutResponse = MutableLiveData<LogoutResponse>()
    val logoutResponse: LiveData<LogoutResponse> = _logoutResponse

    fun postRegister(body: RegisterRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postAccountRegister(body)
        }.onSuccess {
            _registerResponse.value = it
        }.onFailure {
            Log.d(TAG, "get account register api fail ${it.message}")
        }
    }

    fun postLogin(body: LoginRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postAccountLogin(body)
        }.onSuccess {
            _loginResponse.value = it
        }.onFailure {
            Log.d(TAG, "get account login api fail ${it.message}")
        }
    }

    fun getLogout(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getAccountLogout(userId)
        }.onSuccess {
            _logoutResponse.value = it
        }.onFailure {
            Log.d(TAG, "get account logout fail ${it.message}")
        }
    }
}