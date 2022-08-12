package com.example.safetymanagement2022.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.data.remote.model.response.ConnectIotResponse
import com.example.safetymanagement2022.model.HomeData
import com.example.safetymanagement2022.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository): ViewModel() {
    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    private val _connectIotResponse = MutableLiveData<ConnectIotResponse>()
    val connectIotResponse: LiveData<ConnectIotResponse> = _connectIotResponse

    // 데이터 요청
    fun loadHomeData(userId: String) {
        viewModelScope.launch {
            val homeData = homeRepository.getHomeData(userId)
            _homeData.value = homeData
        }
    }

    fun postConnectIot(body: ConnectIotRequest) {
        viewModelScope.launch {
            val connectIotResponse = homeRepository.postConnectIot(body)
            _connectIotResponse.value = connectIotResponse
        }
    }
}