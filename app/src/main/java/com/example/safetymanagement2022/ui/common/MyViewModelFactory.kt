package com.example.safetymanagement2022.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safetymanagement2022.AssetLoader
import com.example.safetymanagement2022.ServiceLoader.provideApiClient
import com.example.safetymanagement2022.repository.building_detail.BuildingDetailRemoteDataSource
import com.example.safetymanagement2022.repository.building_detail.BuildingDetailRepository
import com.example.safetymanagement2022.repository.home.HomeRemoteDataSource
import com.example.safetymanagement2022.repository.home.HomeRepository
import com.example.safetymanagement2022.repository.list_building.ListBuildingRemoteDataSource
import com.example.safetymanagement2022.repository.list_building.ListBuildingRepository
import com.example.safetymanagement2022.repository.list_smartglass.ListSmartGlassRemoteDataSource
import com.example.safetymanagement2022.repository.list_smartglass.ListSmartGlassRepository
import com.example.safetymanagement2022.repository.connect_building.ConnectBuildingRemoteDataSource
import com.example.safetymanagement2022.repository.connect_building.ConnectBuildingRepository
import com.example.safetymanagement2022.repository.connect_smart_glass.ConnectGlassRemoteDataSource
import com.example.safetymanagement2022.repository.connect_smart_glass.ConnectGlassRepository
import com.example.safetymanagement2022.repository.login.LoginRemoteDataSource
import com.example.safetymanagement2022.repository.login.LoginRepository
import com.example.safetymanagement2022.repository.register.RegisterRemoteDataSource
import com.example.safetymanagement2022.repository.register.RegisterRepository
import com.example.safetymanagement2022.ui.building_create.BuildingCreateViewModel
import com.example.safetymanagement2022.ui.building_detail.BuildingDetailViewModel
import com.example.safetymanagement2022.ui.home.HomeViewModel
import com.example.safetymanagement2022.ui.list_building.ListBuildingViewModel
import com.example.safetymanagement2022.ui.list_smartglass.ListSmartGlassViewModel
import com.example.safetymanagement2022.ui.connect_building.ConnectBuildingViewModel
import com.example.safetymanagement2022.ui.connect_smart_glass.ConnectGlassViewModel
import com.example.safetymanagement2022.ui.login.LoginViewModel
import com.example.safetymanagement2022.ui.register.RegisterViewModel

class MyViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            // home
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                val repository = HomeRepository(HomeRemoteDataSource(provideApiClient()))
                HomeViewModel(repository) as T
            }
            // list - building
            modelClass.isAssignableFrom(ListBuildingViewModel::class.java) -> {
                val repository = ListBuildingRepository(ListBuildingRemoteDataSource((AssetLoader(context))))
                ListBuildingViewModel(repository) as T
            }
            // list - smart glass
            modelClass.isAssignableFrom(ListSmartGlassViewModel::class.java) -> {
                val repository = ListSmartGlassRepository(ListSmartGlassRemoteDataSource((AssetLoader(context))))
                ListSmartGlassViewModel(repository) as T
            }
            // building - create
            modelClass.isAssignableFrom(BuildingCreateViewModel::class.java) -> {
                BuildingCreateViewModel() as T
            }
            // building - detail
            modelClass.isAssignableFrom(BuildingDetailViewModel::class.java) -> {
                val repository = BuildingDetailRepository(BuildingDetailRemoteDataSource((AssetLoader(context))))
                BuildingDetailViewModel(repository) as T
            }
            // connect - glass (dialog)
            modelClass.isAssignableFrom(ConnectGlassViewModel::class.java) -> {
                val repository = ConnectGlassRepository(ConnectGlassRemoteDataSource((provideApiClient())))
                ConnectGlassViewModel(repository) as T
            }
            // connect - building (dialog)
            modelClass.isAssignableFrom(ConnectBuildingViewModel::class.java) -> {
                val repository = ConnectBuildingRepository(ConnectBuildingRemoteDataSource((provideApiClient())))
                ConnectBuildingViewModel(repository) as T
            }
            // setting - login
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val repository = LoginRepository(LoginRemoteDataSource((provideApiClient())))
                LoginViewModel(repository) as T
            }
            // setting - register
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                val repository = RegisterRepository(RegisterRemoteDataSource((provideApiClient())))
                RegisterViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}