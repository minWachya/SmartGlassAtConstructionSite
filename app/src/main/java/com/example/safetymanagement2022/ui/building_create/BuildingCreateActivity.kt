package com.example.safetymanagement2022.ui.building_create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.example.safetymanagement2022.databinding.ActivityBuildingCreateBinding

class BuildingCreateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityBuildingCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}