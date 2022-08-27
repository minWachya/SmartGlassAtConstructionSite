package com.example.safetymanagement2022.ui.list.buildingcreate

import android.os.Bundle
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.ActivityBuildingCreateBinding
import com.example.safetymanagement2022.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingCreateActivity: BaseActivity<ActivityBuildingCreateBinding>(R.layout.activity_building_create) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}