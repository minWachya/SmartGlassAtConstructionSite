package com.example.safetymanagement2022.ui.connect_smart_glass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.safetymanagement2022.model.ConnectGlassData
import com.example.safetymanagement2022.repository.connect_smart_glass.ConnectGlassRepository

class ConnectGlassViewModel(private val repository: ConnectGlassRepository): ViewModel() {
    private val _glassList = MutableLiveData<ConnectGlassData>()
    val glassList: LiveData<ConnectGlassData> = _glassList

    init {
        loadConnectGlassData()
    }

    private fun loadConnectGlassData() {
        val glassListData = repository.getConnectGlassData()
        glassListData?.let { data ->
            _glassList.value = data
        }
    }
}