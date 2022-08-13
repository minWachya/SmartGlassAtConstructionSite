package com.example.safetymanagement2022.ui.building_detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.ActivityBuildingDetailBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory
import java.lang.Integer.max
import kotlin.math.min

interface SelectedFloorInterface {
    fun onSelectedFloor(floorText: String, minMax: Int, floor: Int)
}

class BuildingDetailActivity  : AppCompatActivity(), SelectedFloorInterface {
    private lateinit var binding: ActivityBuildingDetailBinding
    private val viewModel: BuildingDetailViewModel by viewModels { MyViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        viewModel.detailData.observe(this@BuildingDetailActivity) { data ->
            binding.detail = data
            binding.rvIssueDetail.adapter = BuildingDetailAdapter(data.issueList)
            setShowSelectFloorDialog(data.minFloor, data.maxFloor)
        }
    }

    private fun setShowSelectFloorDialog(minFloor: Int, maxFloor: Int) {
        binding.tvFloor.setOnClickListener {
            SelectFloorDialog(minFloor, maxFloor).show(supportFragmentManager, "SelectFloorDialog")

        }
    }

    override fun onSelectedFloor(floorText: String, minMax: Int, floor: Int) {
        binding.tvFloor.text = floorText
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}