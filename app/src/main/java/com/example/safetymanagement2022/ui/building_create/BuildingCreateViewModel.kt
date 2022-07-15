package com.example.safetymanagement2022.ui.building_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.ListBuildingData

class BuildingCreateViewModel: ViewModel() {
    // step1: <다음> 버튼 클릭
    private val _openButton1Event = MutableLiveData<Unit>()
    val openButton1Event: LiveData<Unit> get() = _openButton1Event
    // step2: <완료> 버튼 클릭
    private val _openButton2Event = MutableLiveData<Unit>()
    val openButton2Event: LiveData<Unit> get() = _openButton2Event

    fun openBuildingCreateStep2() {
        _openButton1Event.value = Unit
    }
    fun openBuildingCreateFinish() {
        _openButton2Event.value = Unit
    }
}