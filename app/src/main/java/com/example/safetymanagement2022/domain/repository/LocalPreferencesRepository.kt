package com.example.safetymanagement2022.domain.repository

interface LocalPreferencesRepository {
    fun getUserId(): String
    fun getUserPw(): String
    fun setUserId(id: String)
    fun setUserPw(pw: String)
    fun deleteUserInfo()
}