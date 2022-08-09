package com.example.safetymanagement2022.repository.home

import com.example.safetymanagement2022.model.HomeData

interface HomeDataSource {
    suspend fun getHomeData(userId: String): HomeData?
}