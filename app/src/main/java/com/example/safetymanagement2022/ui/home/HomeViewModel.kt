package com.example.safetymanagement2022.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.HomeData
import com.example.safetymanagement2022.repository.home.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository): ViewModel() {
    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    init {
        loadHomeData()
    }

    // 데이터 요청
    private fun loadHomeData() {
        val homeData = homeRepository.getHomeData()
        homeData?.let { data ->
            _homeData.value = data
        }
    }
}