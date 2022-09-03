package com.example.safetymanagement2022.ui.list.building

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.response.ListBuildingResponse
import com.example.safetymanagement2022.data.remote.repository.ListRepository
import com.example.safetymanagement2022.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListBuildingViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {
    private val _listBuildingResponse = MutableLiveData<ListBuildingResponse>()
    val listBuildingResponse: LiveData<ListBuildingResponse> = _listBuildingResponse

    private val _openCreateBuildingEvent = MutableLiveData<Event<Unit>>()
    val openCreateBuildingEvent: LiveData<Event<Unit>> get() = _openCreateBuildingEvent

    private val _openBuildingDetailEvent = MutableLiveData<Event<String>>()
    val openBuildingDetailEvent: LiveData<Event<String>> get() = _openBuildingDetailEvent


    fun getListBuilding(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getListBuilding(userId)
        }.onSuccess {
            _listBuildingResponse.value = it
        }.onFailure {
            Log.d(TAG, "get list building api fail ${it.message}")
        }
    }

    fun openCreateBuilding() {
        _openCreateBuildingEvent.value = Event(Unit)
    }

    fun openBuildingDetail(buildingId: String) {
        _openBuildingDetailEvent.value = Event(buildingId)
    }
}