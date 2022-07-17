package com.example.safetymanagement2022.ui.building_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.BuildingDetailData
import com.example.safetymanagement2022.repository.building_detail.BuildingDetailRepository

class BuildingDetailViewModel(private val repository: BuildingDetailRepository): ViewModel() {
    private val _detailData = MutableLiveData<BuildingDetailData>()
    val detailData: LiveData<BuildingDetailData> = _detailData

    init {
        loadBuildingDetailData()
    }

    private fun loadBuildingDetailData() {
        val detailData = repository.getBuildingDetailData()
        detailData?.let { data ->
            _detailData.value = data
        }
    }
}