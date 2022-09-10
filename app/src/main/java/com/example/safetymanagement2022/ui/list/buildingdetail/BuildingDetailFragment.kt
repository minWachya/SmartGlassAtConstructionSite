package com.example.safetymanagement2022.ui.list.buildingdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.GlideApp
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.*
import com.example.safetymanagement2022.databinding.FragmentBuildingDetailBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingDetailFragment: BaseFragment<FragmentBuildingDetailBinding>(R.layout.fragment_building_detail) {
    private val viewModel: BuildingDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setBackBtnClickListener()
        setLayout()

        viewModel.buildingDetail.observe(viewLifecycleOwner) { data ->
            binding.detail = data
            binding.rvIssueDetail.adapter = BuildingDetailAdapter(data.issueList)
            binding.tvFloor.text = if(data.minFloor == 0) "지상 1층" else if(data.maxFloor == 0) "지하 1층" else "지상 1층"
            setShowSelectFloorDialog(data.minFloor, data.maxFloor)
            setDrawing(if(data.minFloor == 0) 1 else if(data.maxFloor == 0) 0 else 1, 1)
            val arrFloor = setFloorList(data.minFloor, data.maxFloor)
            setSpinnerBtn(arrFloor)
        }
    }

    private fun setLayout() {
        val buildingId = requireArguments().getInt(KEY_BUILDING_DETAIL_ID)
        viewModel.getBuildingDetail(buildingId)
    }

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setFloorList(minFloor: Int, maxFloor: Int): ArrayList<String> {
        val arrTemp = arrayListOf<String>()
        for (i in minFloor downTo 1) arrTemp.add("지하 ${i}층")
        for(i in 1..maxFloor) arrTemp.add("지상 ${i}층")
        return arrTemp
    }

    private fun setSpinnerBtn(arrFloor: ArrayList<String>) {
        binding.ivLeft.setOnClickListener {
            val curIndex = arrFloor.indexOf(binding.tvFloor.text.toString())
            if(curIndex > 0){
                binding.tvFloor.text = arrFloor[curIndex-1]
                ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(arrFloor[curIndex-1])
            }
        }
        binding.ivRight.setOnClickListener {
            val curIndex = arrFloor.indexOf(binding.tvFloor.text.toString())
            if(curIndex != arrFloor.size-1){
                binding.tvFloor.text = arrFloor[curIndex+1]
                ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(arrFloor[curIndex+1])
            }
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
            try{
                GlideApp.with(requireActivity())
                    .load(data.drawingList[imgUrlIndex])
                    .into(binding.ivDrawing)
            } catch(e: IndexOutOfBoundsException){
               Log.d("mmm detail frag", "index error")
             }
        }
    }

    private fun setShowSelectFloorDialog(minFloor: Int, maxFloor: Int) {
        binding.tvFloor.setOnClickListener {
            SelectFloorDialog(minFloor, maxFloor).show(parentFragmentManager, "SelectFloorDialog")
        }
        parentFragmentManager.setFragmentResultListener(KEY_DIALOG_DETAIL,
            viewLifecycleOwner) { _, bundle ->
            val floorText = bundle.get(KEY_DIALOG_DETAIL_TEXT).toString()
            val minMax = bundle.get(KEY_DIALOG_DETAIL_MIN_MAX).toString().toInt()
            val floor = bundle.get(KEY_DIALOG_DETAIL_FLOOR).toString().toInt()
            binding.tvFloor.text = floorText
            ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(floorText)
            setDrawing(minMax, floor)
        }
        ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(binding.tvFloor.text)
    }

}