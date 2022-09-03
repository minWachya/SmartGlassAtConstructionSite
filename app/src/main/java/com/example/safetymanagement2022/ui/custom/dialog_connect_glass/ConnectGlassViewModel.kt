package com.example.safetymanagement2022.ui.custom.dialog_connect_glass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.response.ConnectGlassResponse
import com.example.safetymanagement2022.data.remote.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectGlassViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {
    private val _glassList = MutableLiveData<ConnectGlassResponse>()
    val glassList: LiveData<ConnectGlassResponse> = _glassList

    fun getConnectGlass(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getConnectGlass(userId)
        }.onSuccess {
            _glassList.value = it
        }.onFailure {
            Log.d(TAG, "get home connect glass api fail ${it.message}")
        }
    }
}