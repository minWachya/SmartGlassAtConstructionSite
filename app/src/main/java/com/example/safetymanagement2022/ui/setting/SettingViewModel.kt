package com.example.safetymanagement2022.ui.setting

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
import com.example.safetymanagement2022.data.remote.repository.SettingRepository
import com.example.safetymanagement2022.domain.repository.LocalPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository,
    private val loginRepository: LocalPreferencesRepository
): ViewModel() {
    private val _logoutResponse = MutableLiveData<LogoutResponse>()
    val logoutResponse: LiveData<LogoutResponse> = _logoutResponse
    private val _changePwResponse = MutableLiveData<LoginResponse>()
    val changePwResponse: LiveData<LoginResponse> = _changePwResponse
    private val _changeUserInfoResponse = MutableLiveData<RegisterResponse>()
    val changeUserInfoResponse: LiveData<RegisterResponse> = _changeUserInfoResponse

    fun getLogout(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getAccountLogout(userId)
        }.onSuccess {
            _logoutResponse.value = it
            // 사용자 정보 해제(자동 로그인 해제)
            loginRepository.deleteUserId()
        }.onFailure {
            Log.d(TAG, "get account logout fail ${it.message}")
        }
    }

    fun putChangeUserInfo(body: RegisterRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.putSettingChangeUserInfo(getUserId(), body)
        }.onSuccess {
            _changeUserInfoResponse.value = it
        }.onFailure {
            Log.d(TAG, "get account register api fail ${it.message}")
        }
    }

    fun putChangePw(body: LoginRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.putSettingChangePw(getUserId(), body)
        }.onSuccess {
            _changePwResponse.value = it

            // 로그인 정보 저장
            loginRepository.setUserId(body.id)
        }.onFailure {
            Log.d(TAG, "get account login api fail ${it.message}")
        }
    }


    fun getUserId(): String =
        loginRepository.getUserId()
}