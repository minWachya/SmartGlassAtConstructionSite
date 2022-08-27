package com.example.safetymanagement2022.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.ConnectIotResponse
import com.example.safetymanagement2022.data.remote.model.response.DisConnectResponse
import com.example.safetymanagement2022.data.remote.model.response.HomeResponse
import com.example.safetymanagement2022.data.remote.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {
    private val _homeResponse = MutableLiveData<HomeResponse>()
    val homeResponse: LiveData<HomeResponse> = _homeResponse

    private val _connectIotResponse = MutableLiveData<ConnectIotResponse>()
    val connectIotResponse: LiveData<ConnectIotResponse> = _connectIotResponse

    private val _disConnectIotResponse = MutableLiveData<DisConnectResponse>()
    val disConnectIotResponse: LiveData<DisConnectResponse> = _disConnectIotResponse

    fun getHomeResponse(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getHome(userId)
        }.onSuccess {
            _homeResponse.value = it
        }.onFailure {
            Log.d(TAG, "get home api fail ${it.message}")
        }
    }

    fun postConnectIot(body: ConnectIotRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postConnectIot(body)
        }.onSuccess {
            _connectIotResponse.value = it
        }.onFailure {
            Log.d(TAG, "get home connect iot fail ${it.message}")
        }
    }
    fun getDisConnectIot(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getDisConnectIot(userId)
        }.onSuccess {
            _disConnectIotResponse.value = it
        }.onFailure {
            Log.d(TAG, "get home disconnect iot fail ${it.message}")
        }
    }
}