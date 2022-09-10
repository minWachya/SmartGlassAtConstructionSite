package com.example.safetymanagement2022.domain.repository

interface LocalPreferencesRepository {
    fun getUserId(): String
    fun setUserId(id: String)
    fun deleteUserId()
}