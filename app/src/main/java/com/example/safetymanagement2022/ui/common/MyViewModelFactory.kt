package com.example.safetymanagement2022.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.repository.home.HomeRemoteDataSource
import com.example.safetymanagement2022.repository.home.HomeRepository
import com.example.safetymanagement2022.repository.list_building.ListBuildingRemoteDataSource
import com.example.safetymanagement2022.repository.list_building.ListBuildingRepository
import com.example.safetymanagement2022.ui.home.HomeViewModel
import com.example.safetymanagement2022.ui.list_building.ListBuildingViewModel

class MyViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            // home
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeRemoteDataSource((AssetLoader(context))))
                HomeViewModel(repository) as T
            }
            // list - building
            modelClass.isAssignableFrom(ListBuildingViewModel::class.java) -> {
                val repository = ListBuildingRepository(ListBuildingRemoteDataSource((AssetLoader(context))))
                ListBuildingViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}