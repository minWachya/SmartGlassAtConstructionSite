package com.example.safetymanagement2022.ui.list.buildingcreate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate1Request
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate2Request
import com.example.safetymanagement2022.data.remote.model.response.BuildingCreate1Response
import com.example.safetymanagement2022.data.remote.model.response.BuildingCreate2Response
import com.example.safetymanagement2022.data.remote.repository.ListRepository
import com.example.safetymanagement2022.domain.repository.LocalPreferencesRepository
import com.example.safetymanagement2022.model.FloorPlanData
import com.example.safetymanagement2022.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildingCreateViewModel @Inject constructor(
    private val repository: ListRepository,
    private val loginRepository: LocalPreferencesRepository
): ViewModel() {
    // 1단계 건물 생성
    private val _buildingCreate1Response = MutableLiveData<BuildingCreate1Response>()
    val buildingCreate1Response: LiveData<BuildingCreate1Response> = _buildingCreate1Response
    // 2단계 건물 생성
    private val _buildingCreate2Response = MutableLiveData<BuildingCreate2Response>()
    val buildingCreate2Response: LiveData<BuildingCreate2Response> = _buildingCreate2Response
    // s3에서 받아온 url 배열
    private val _arrS3Url = MutableLiveData<ArrayList<String>>()
    val arrS3Url: LiveData<ArrayList<String>> = _arrS3Url
    // step1: <다음> 버튼 클릭
    private val _openButton1Event = MutableLiveData<Event<Unit>>()
    val openButton1Event: LiveData<Event<Unit>> get() = _openButton1Event
    // step2: <완료> 버튼 클릭
    private val _openButton2Event = MutableLiveData<Event<Unit>>()
    val openButton2Event: LiveData<Event<Unit>> get() = _openButton2Event
    // step2: 건물 층, 건물 도면 배열
    private val _listFloorPlan = MutableLiveData<List<FloorPlanData>>()
    val listFloorPlan: LiveData<List<FloorPlanData>> get() = _listFloorPlan

    fun setArrS3Url(list: ArrayList<String>) {
        _arrS3Url.value = list
    }

    fun postBuildingCreate1(userId: String, body: BuildingCreate1Request) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postBuildingCreate1(userId, body)
        }.onSuccess {
            _buildingCreate1Response.value = it
        }.onFailure {
            Log.d(TAG, "get building create1 api fail ${it.message}")
        }
    }

    fun postBuildingCreate2(userId: String, body: BuildingCreate2Request) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postBuildingCreate2(userId, body)
        }.onSuccess {
            _buildingCreate2Response.value = it
        }.onFailure {
            Log.d(TAG, "get list create glass api fail ${it.message}")
        }
    }

    fun openBuildingCreateStep2() {
        _openButton1Event.value = Event(Unit)
    }
    fun openBuildingCreateFinish() {
        _openButton2Event.value = Event(Unit)
    }
    fun setListFloorPlan(list: List<FloorPlanData>) {
        _listFloorPlan.value = list
    }

    fun getUserId(): String = loginRepository.getUserId()
}