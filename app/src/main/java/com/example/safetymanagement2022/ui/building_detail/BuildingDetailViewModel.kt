package com.example.safetymanagement2022.ui.building_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.response.BuildingDetailResponse
import com.example.safetymanagement2022.data.remote.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildingDetailViewModel @Inject constructor(
    private val repository: ListRepository
): ViewModel() {
    private val _buildingDetail = MutableLiveData<BuildingDetailResponse>()
    val buildingDetail: LiveData<BuildingDetailResponse> = _buildingDetail

    fun getBuildingDetail(buildingId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getBuildingDetail(buildingId)
        }.onSuccess {
            _buildingDetail.value = it
        }.onFailure {
            Log.d(TAG, "get list building detail api fail ${it.message}")
        }
    }
}