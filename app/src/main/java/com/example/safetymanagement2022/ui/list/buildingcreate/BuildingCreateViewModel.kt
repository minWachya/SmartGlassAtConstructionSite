package com.example.safetymanagement2022.ui.list.buildingcreate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.FloorPlanData
import com.example.safetymanagement2022.ui.common.Event

class BuildingCreateViewModel: ViewModel() {
    // step1: <다음> 버튼 클릭
    private val _openButton1Event = MutableLiveData<Event<Unit>>()
    val openButton1Event: LiveData<Event<Unit>> get() = _openButton1Event
    // step2: <완료> 버튼 클릭
    private val _openButton2Event = MutableLiveData<Event<Unit>>()
    val openButton2Event: LiveData<Event<Unit>> get() = _openButton2Event
    // step2: 건물 층, 건물 도면 배열
    private val _listFloorPlan = MutableLiveData<List<FloorPlanData>>()
    val listFloorPlan: LiveData<List<FloorPlanData>> get() = _listFloorPlan

    fun openBuildingCreateStep2() {
        _openButton1Event.value = Event(Unit)
    }
    fun openBuildingCreateFinish() {
        _openButton2Event.value = Event(Unit)
    }
    fun setListFloorPlan(list: List<FloorPlanData>) {
        _listFloorPlan.value = list
    }
}