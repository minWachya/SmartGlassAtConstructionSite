package com.example.safetymanagement2022.domain.repository

import com.example.safetymanagement2022.data.local.LocalPreferencesDataSource
import javax.inject.Inject

class LocalPreferencesRepositoryImpl @Inject constructor(
    private val loginDataSource: LocalPreferencesDataSource
): LocalPreferencesRepository {
    override fun getUserId(): String = loginDataSource.getUserId()
    override fun setUserId(id: String) = loginDataSource.setUserId(id)
    override fun deleteUserId() = loginDataSource.deleteUserId()
}