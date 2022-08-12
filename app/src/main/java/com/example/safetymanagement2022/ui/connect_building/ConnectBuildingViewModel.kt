package com.example.safetymanagement2022.ui.connect_building

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.model.ConnectBuildingData
import com.example.safetymanagement2022.repository.connect_building.ConnectBuildingRepository
import kotlinx.coroutines.launch

class ConnectBuildingViewModel(private val repository: ConnectBuildingRepository): ViewModel() {
    private val _buildingList = MutableLiveData<ConnectBuildingData>()
    val buildingList: LiveData<ConnectBuildingData> = _buildingList

    fun loadConnectBuildingData(userId: String) {
        viewModelScope.launch {
            val connectGlassData = repository.getConnectBuildingData(userId)
            _buildingList.value = connectGlassData
        }
    }
}