package com.example.safetymanagement2022.data.local

interface LocalPreferencesDataSource {
    fun getUserId(): String
    fun getUserPw(): String
    fun setUserId(id: String)
    fun setUserPw(pw: String)
    fun deleteUserInfo()
}