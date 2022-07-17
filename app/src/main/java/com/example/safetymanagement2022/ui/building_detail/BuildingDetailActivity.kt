package com.example.safetymanagement2022.ui.building_detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.databinding.ActivityBuildingDetailBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

class BuildingDetailActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityBuildingDetailBinding
    private val viewModel: BuildingDetailViewModel by viewModels { MyViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.detailData.observe(this@BuildingDetailActivity) { data ->
            binding.detail = data
            binding.rvIssueDetail.adapter = BuildingDetailAdapter(data.issueList)
        }

    }

}