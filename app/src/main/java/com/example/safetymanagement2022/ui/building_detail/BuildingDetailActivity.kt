package com.example.safetymanagement2022.ui.building_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.databinding.ActivityBuildingDetailBinding

class BuildingDetailActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityBuildingDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}