package com.example.safetymanagement2022.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safetymanagement2022.data.remote.model.request.RegisterRequest
import com.example.safetymanagement2022.data.remote.model.response.RegisterResponse
import com.example.safetymanagement2022.repository.register.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository): ViewModel() {
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    fun postRegister(body: RegisterRequest) {
        viewModelScope.launch {
            val response = repository.postRegister(body)
            _registerResponse.value = response
        }
    }

}