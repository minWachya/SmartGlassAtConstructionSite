package com.example.safetymanagement2022.ui.building_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MAX
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MIN
import com.example.safetymanagement2022.common.KEY_BUILDING_MEMO
import com.example.safetymanagement2022.common.KEY_BUILDING_NAME
import com.example.safetymanagement2022.databinding.FragmentBuildingCreate2Binding
import com.example.safetymanagement2022.model.FloorPlanData
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

class BuildingCreate2Fragment : Fragment() {
    private lateinit var binding: FragmentBuildingCreate2Binding
    private val viewModel: BuildingCreateViewModel by viewModels { MyViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuildingCreate2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val buildingName = requireArguments().getString(KEY_BUILDING_NAME)
        val memo = requireArguments().getString(KEY_BUILDING_MEMO)
        val floorMax = requireArguments().getString(KEY_BUILDING_FLOOR_MAX)?.toIntOrNull() ?: 0
        val floorMin = requireArguments().getString(KEY_BUILDING_FLOOR_MIN)?.toIntOrNull() ?: 0

        val list = getFloorList(floorMax, floorMin)
        viewModel.setListFloorPlan(list)
        setNavigation()

        viewModel.listFloorPlan.observe(viewLifecycleOwner) {
            binding.rvFloorPlan.adapter = BuildingCreateAdapter(viewModel).apply {
                submitList(list)
            }
        }
        viewModel.openButton2Event.observe(viewLifecycleOwner) {
            openBuildingCreateFinish()
        }

    }

    private fun openBuildingCreateFinish() {
        Toast.makeText(context, "끝!!", Toast.LENGTH_LONG).show()
    }

    private fun setNavigation() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getFloorList(floorMax: Int, floorMin: Int): List<FloorPlanData> {
        val list = mutableListOf<FloorPlanData>()
        for (i in floorMax downTo 1) list.add(FloorPlanData("지상 " + i + "층", null))
        for (i in 1..floorMin) list.add(FloorPlanData("지하 " + i + "층", null))
        return list
    }

}