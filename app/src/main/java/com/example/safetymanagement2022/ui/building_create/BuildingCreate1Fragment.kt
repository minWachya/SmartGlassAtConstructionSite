package com.example.safetymanagement2022.ui.building_create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.addTextChangedListener
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MAX
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MIN
import com.example.safetymanagement2022.common.KEY_BUILDING_MEMO
import com.example.safetymanagement2022.common.KEY_BUILDING_NAME
import com.example.safetymanagement2022.databinding.FragmentBuildingCreate1Binding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory
import com.example.safetymanagement2022.ui.list_building.ListBuildingViewModel

class BuildingCreate1Fragment : Fragment() {
    private lateinit var binding: FragmentBuildingCreate1Binding
    private val viewModel: BuildingCreateViewModel by viewModels { MyViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuildingCreate1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.openButton1Event.observe(viewLifecycleOwner) {
            openBuildingCreateStep2()
        }

        setButtonEnableListener()
    }

    private fun setButtonEnableListener() {
        // 건물명
        binding.editBuildingName.addTextChangedListener { btnEnableCheck() }
        // 상세 정보
        binding.editMemo.addTextChangedListener { btnEnableCheck() }
        // 지상
        binding.editFloorMax.addTextChangedListener { btnEnableCheck() }
        // 지하
        binding.editFloorMin.addTextChangedListener { btnEnableCheck() }
    }

    private fun btnEnableCheck() {
        val checkBuildingName = binding.editBuildingName.text.trim().toString().isNotEmpty()
        val checkMemo = binding.editMemo.text.trim().toString().isNotEmpty()
        val checkFloorMax = binding.editFloorMax.text.trim().toString().isNotEmpty()
        val checkFloorMin = binding.editFloorMin.text.trim().toString().isNotEmpty()
        binding.btnNext.isEnabled = checkBuildingName && checkMemo && checkFloorMax && checkFloorMin
    }

    private fun openBuildingCreateStep2() {
        val buildingName = binding.editBuildingName.text.trim().toString()
        val memo = binding.editMemo.text.trim().toString()
        val floorMax = binding.editFloorMax.text.trim().toString()
        val floorMin = binding.editFloorMin.text.trim().toString()

        findNavController().navigate(R.id.action_building_create_1_to_building_create_2,
            bundleOf(
                KEY_BUILDING_NAME to buildingName,
                KEY_BUILDING_MEMO to memo,
                KEY_BUILDING_FLOOR_MAX to floorMax,
                KEY_BUILDING_FLOOR_MIN to floorMin
            ))
    }

}