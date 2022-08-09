package com.example.safetymanagement2022.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.model.HomeData
import com.example.safetymanagement2022.repository.home.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository): ViewModel() {
    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    // 데이터 요청
    fun loadHomeData(userId: String) {
        viewModelScope.launch {
            val homeData = homeRepository.getHomeData(userId)
            _homeData.value = homeData
        }
    }
}