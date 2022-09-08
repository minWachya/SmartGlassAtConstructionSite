package com.example.safetymanagement2022.ui.setting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.response.LogoutResponse
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

    fun getLogout(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getAccountLogout(userId)
        }.onSuccess {
            _logoutResponse.value = it
            // 사용자 정보 해제(자동 로그인 해제)
            loginRepository.deleteUserInfo()
        }.onFailure {
            Log.d(TAG, "get account logout fail ${it.message}")
        }
    }

    fun getUserId(): String =
        loginRepository.getUserId()
}