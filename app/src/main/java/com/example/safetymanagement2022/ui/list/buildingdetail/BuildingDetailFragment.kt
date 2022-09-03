package com.example.safetymanagement2022.ui.list.buildingdetail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.GlideApp
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.TAG
import com.example.safetymanagement2022.databinding.FragmentBuildingDetailBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

interface SelectedFloorInterface {
    fun onSelectedFloor(floorText: String, minMax: Int, floor: Int)
}

@AndroidEntryPoint
class BuildingDetailFragment: BaseFragment<FragmentBuildingDetailBinding>(R.layout.fragment_building_detail),
    SelectedFloorInterface {
    private val viewModel: BuildingDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setBackBtnClickListener()

        viewModel.buildingDetail.observe(viewLifecycleOwner) { data ->
            binding.detail = data
            binding.rvIssueDetail.adapter = BuildingDetailAdapter(data.issueList)
            setShowSelectFloorDialog(data.minFloor, data.maxFloor)
            setDrawing(1, 1)
        }
    }

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setDrawing(minMax: Int, floor: Int) {
        // minMax: 지하 0, 지상 1
        // floor: 1층: 0, 2층: 1, 3층: 2
        // 지상 1층 = 1 0
        // min 1 Max 3에서 [3 2 1 1]
        // 지상 1층의 drawingList 위치 = drawingList[2] = max - floor = 3 - 1 = 2
        // 지하 1층의 drawingList 위치 = drawingList[3] = min + max - floor = 1 + 3 - 1 = 3
        val data = viewModel.buildingDetail.value
        if (data != null) {
            val imgUrlIndex: Int = if(minMax == 1) data.maxFloor - (floor + 1)
                                    else data.maxFloor + data.minFloor - (floor + 1)
            GlideApp.with(requireActivity())
                .load(data.drawingList[imgUrlIndex])
                .into(binding.ivDrawing)
        }
    }

    private fun setShowSelectFloorDialog(minFloor: Int, maxFloor: Int) {
        binding.tvFloor.setOnClickListener {
            SelectFloorDialog(minFloor, maxFloor).show(parentFragmentManager, "SelectFloorDialog")
        }
        ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter("지상 1층")
    }

    override fun onSelectedFloor(floorText: String, minMax: Int, floor: Int) {
        binding.tvFloor.text = floorText
        ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(floorText)
        setDrawing(minMax, floor)
    }

}