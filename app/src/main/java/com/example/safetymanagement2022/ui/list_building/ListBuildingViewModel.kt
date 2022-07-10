package com.example.safetymanagement2022.ui.list_building

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.ListBuildingData
import com.example.safetymanagement2022.repository.list_building.ListBuildingRepository

class ListBuildingViewModel(private val listBuildingRepository: ListBuildingRepository): ViewModel() {
    private val _listBuildingData = MutableLiveData<ListBuildingData>()
    val listBuildingData: LiveData<ListBuildingData> = _listBuildingData

    init {
        loadListBuildingDate()
    }

    private fun loadListBuildingDate() {
        val listBuildingData = listBuildingRepository.getListBuildingData()
        listBuildingData?.let { data ->
            _listBuildingData.value = data
        }
    }
}