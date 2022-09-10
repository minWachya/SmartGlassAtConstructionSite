package com.example.safetymanagement2022.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalPreferencesDataSourceImpl @Inject constructor(
    private val localPreferences: SharedPreferences
): LocalPreferencesDataSource {
    companion object {
        private const val USER_ID = "user id"
    }

    override fun getUserId(): String =
        localPreferences.getString(USER_ID, "") ?: ""


    override fun setUserId(id: String) =
        localPreferences.edit().putString(USER_ID, id).apply()


    override fun deleteUserId() {
        localPreferences.edit()
            .remove(USER_ID)
            .apply()
    }


}