package com.example.safetymanagement2022.ui.connect_smart_glass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.model.ConnectGlassData
import com.example.safetymanagement2022.repository.connect_smart_glass.ConnectGlassRepository
import kotlinx.coroutines.launch

class ConnectGlassViewModel(private val repository: ConnectGlassRepository): ViewModel() {
    private val _glassList = MutableLiveData<ConnectGlassData>()
    val glassList: LiveData<ConnectGlassData> = _glassList

    fun loadConnectGlassData(userId: String) {
        viewModelScope.launch {
            val connectGlassData = repository.getConnectGlassData(userId)
            _glassList.value = connectGlassData
        }
    }
}