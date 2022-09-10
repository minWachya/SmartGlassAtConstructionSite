package com.example.safetymanagement2022.data.local

interface LocalPreferencesDataSource {
    fun getUserId(): String
    fun setUserId(id: String)
    fun deleteUserId()
}