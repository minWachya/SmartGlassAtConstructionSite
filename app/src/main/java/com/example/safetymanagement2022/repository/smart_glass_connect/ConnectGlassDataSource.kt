package com.example.safetymanagement2022.repository.smart_glass_connect

import com.example.safetymanagement2022.model.ConnectGlassData

interface ConnectGlassDataSource {
    fun getConnectGlassData(): ConnectGlassData?
}