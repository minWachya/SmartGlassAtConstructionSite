package com.example.safetymanagement2022.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalPreferencesDataSourceImpl @Inject constructor(
    private val localPreferences: SharedPreferences
): LocalPreferencesDataSource {
    companion object {
        private const val USER_ID = "user id"
        private const val USER_PW = "user pw"
    }

    override fun getUserId(): String =
        localPreferences.getString(USER_ID, "") ?: ""

    override fun getUserPw(): String =
        localPreferences.getString(USER_PW, "") ?: ""

    override fun setUserId(id: String) =
        localPreferences.edit().putString(USER_ID, id).apply()

    override fun setUserPw(pw: String) =
        localPreferences.edit().putString(USER_PW, pw).apply()

    override fun deleteUserInfo() {
        localPreferences.edit()
            .remove(USER_ID)
            .remove(USER_PW)
            .apply()
    }


}