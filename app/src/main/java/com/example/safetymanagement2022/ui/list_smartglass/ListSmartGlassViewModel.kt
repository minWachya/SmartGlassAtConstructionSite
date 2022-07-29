package com.example.safetymanagement2022.ui.list_smartglass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.ListSmartGlassData
import com.example.safetymanagement2022.repository.list_smartglass.ListSmartGlassRepository

class ListSmartGlassViewModel(private val listSmartGlassRepository: ListSmartGlassRepository): ViewModel() {
    private val _listSmartGlassData = MutableLiveData<ListSmartGlassData>()
    val listSmartGlassData: LiveData<ListSmartGlassData> = _listSmartGlassData

    private val _openCreateGlassEvent = MutableLiveData<Unit>()
    val openCreateGlassEvent: LiveData<Unit> get() = _openCreateGlassEvent

    init {
        loadListBuildingData()
    }

    private fun loadListBuildingData() {
        val listSmartGlassData = listSmartGlassRepository.getListSmartGlassData()
        listSmartGlassData?.let { data ->
            _listSmartGlassData.value = data
        }
    }

    fun openCreateGlass() {
        _openCreateGlassEvent.value = Unit
    }
}