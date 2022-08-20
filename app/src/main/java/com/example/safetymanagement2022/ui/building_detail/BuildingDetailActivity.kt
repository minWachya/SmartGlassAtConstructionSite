package com.example.safetymanagement2022.ui.building_detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.safetymanagement2022.GlideApp
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
            setDrawing(1, 1)
        }
    }

    private fun setDrawing(minMax: Int, floor: Int) {
        // minMax: 지하 0, 지상 1
        // floor: 1층: 0, 2층: 1, 3층: 2
        // 지상 1층 = 1 0
        // min 1 Max 3에서 [3 2 1 1]
        // 지상 1층의 drawingList 위치 = drawingList[2] = max - floor = 3 - 1 = 2
        // 지하 1층의 drawingList 위치 = drawingList[3] = min + max - floor = 1 + 3 - 1 = 3
        val data = viewModel.detailData.value
        if (data != null) {
            val imgUrlIndex: Int = if(minMax == 1) data.maxFloor - (floor + 1)
                                    else data.maxFloor + data.minFloor - (floor + 1)
            GlideApp.with(applicationContext)
                .load(data.drawingList[imgUrlIndex])
                .into(binding.ivDrawing)
        }
    }

    private fun setShowSelectFloorDialog(minFloor: Int, maxFloor: Int) {
        binding.tvFloor.setOnClickListener {
            SelectFloorDialog(minFloor, maxFloor).show(supportFragmentManager, "SelectFloorDialog")
        }
        ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter("지상 1층")
    }

    override fun onSelectedFloor(floorText: String, minMax: Int, floor: Int) {
        binding.tvFloor.text = floorText
        ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(floorText)
        setDrawing(minMax, floor)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}