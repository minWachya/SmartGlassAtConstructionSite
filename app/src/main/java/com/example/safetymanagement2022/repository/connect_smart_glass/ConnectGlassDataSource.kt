package com.example.safetymanagement2022.repository.connect_smart_glass

import com.example.safetymanagement2022.model.ConnectGlassData

interface ConnectGlassDataSource {
    fun getConnectGlassData(): ConnectGlassData?
}