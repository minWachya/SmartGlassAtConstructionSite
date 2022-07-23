package com.example.safetymanagement2022.ui.connect_building

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.ConnectBuildingData
import com.example.safetymanagement2022.repository.connect_building.ConnectBuildingRepository

class ConnectBuildingViewModel(private val repository: ConnectBuildingRepository): ViewModel() {
    private val _buildingList = MutableLiveData<ConnectBuildingData>()
    val buildingList: LiveData<ConnectBuildingData> = _buildingList

    init {
        loadConnectBuildingData()
    }

    private fun loadConnectBuildingData() {
        val buildingListData = repository.getConnectBuildingData()
        buildingListData?.let { data ->
            _buildingList.value = data
        }
    }
}