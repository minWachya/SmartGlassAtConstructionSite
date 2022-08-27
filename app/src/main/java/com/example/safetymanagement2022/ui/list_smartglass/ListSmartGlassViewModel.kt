package com.example.safetymanagement2022.ui.list_smartglass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.response.ListSmartGlassResponse
import com.example.safetymanagement2022.data.remote.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListSmartGlassViewModel@Inject constructor(
    private val repository: ListRepository
): ViewModel() {
    private val _listGlassResponse = MutableLiveData<ListSmartGlassResponse>()
    val listGlassResponse: LiveData<ListSmartGlassResponse> = _listGlassResponse

    private val _openCreateGlassEvent = MutableLiveData<Unit>()
    val openCreateGlassEvent: LiveData<Unit> get() = _openCreateGlassEvent

    fun getListGlass(userId: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.getListGlass(userId)
        }.onSuccess {
            _listGlassResponse.value = it
        }.onFailure {
            Log.d(TAG, "get list glass api fail ${it.message}")
        }
    }

    fun openCreateGlass() {
        _openCreateGlassEvent.value = Unit
    }
}