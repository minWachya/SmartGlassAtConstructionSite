package com.example.safetymanagement2022.ui.list.glasscreate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.data.remote.model.response.GlassCreateResponse
import com.example.safetymanagement2022.data.remote.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlassCreateViewModel @Inject constructor(
    private val repository: ListRepository
): ViewModel() {
    private val _glassCreateResponse = MutableLiveData<GlassCreateResponse>()
    val glassCreateResponse: LiveData<GlassCreateResponse> = _glassCreateResponse

    fun postGlassCreate(userId: String, body: GlassCreateRequest) = viewModelScope.launch {
        kotlin.runCatching {
            repository.postGlassCreate(userId, body)
        }.onSuccess {
            _glassCreateResponse.value = it
        }.onFailure {
            Log.d(TAG, "get list create glass api fail ${it.message}")
        }
    }

}