package com.example.safetymanagement2022.ui.list.glass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.response.ListSmartGlassResponse
import com.example.safetymanagement2022.data.remote.repository.ListRepository
import com.example.safetymanagement2022.domain.repository.LocalPreferencesRepository
import com.example.safetymanagement2022.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListGlassViewModel@Inject constructor(
    private val repository: ListRepository,
    private val loginRepository: LocalPreferencesRepository
): ViewModel() {
    private val _listGlassResponse = MutableLiveData<ListSmartGlassResponse>()
    val listGlassResponse: LiveData<ListSmartGlassResponse> = _listGlassResponse

    private val _openCreateGlassEvent = MutableLiveData<Event<Unit>>()
    val openCreateGlassEvent: LiveData<Event<Unit>> get() = _openCreateGlassEvent

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
        _openCreateGlassEvent.value = Event(Unit)
    }

    fun getUserId(): String = loginRepository.getUserId()
}