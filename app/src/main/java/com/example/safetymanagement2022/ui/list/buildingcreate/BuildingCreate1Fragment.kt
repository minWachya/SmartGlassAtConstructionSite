package com.example.safetymanagement2022.ui.list.buildingcreate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.*
import com.example.safetymanagement2022.data.remote.model.request.BuildingCreate1Request
import com.example.safetymanagement2022.databinding.FragmentBuildingCreate1Binding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingCreate1Fragment: BaseFragment<FragmentBuildingCreate1Binding>(R.layout.fragment_building_create_1) {
    private val viewModel: BuildingCreateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.openButton1Event.observe(viewLifecycleOwner, EventObserver {
            postBuildingCreate1()
        })
        viewModel.buildingCreate1Response.observe(viewLifecycleOwner) {
            openBuildingCreateStep2()
        }

        setBackBtnClickListener()
        setButtonEnableListener()
    }

    private fun postBuildingCreate1() {
        val buildingName = binding.editBuildingName.text.trim().toString()
        val memo = binding.editMemo.text.trim().toString()
        val floorMax = binding.editFloorMax.text.trim().toString().toInt()
        val floorMin = binding.editFloorMin.text.trim().toString().toInt()
        val body = BuildingCreate1Request(buildingName, memo, floorMax, floorMin)
        viewModel.postBuildingCreate1(viewModel.getUserId(), body)
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

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun btnEnableCheck() {
        val checkBuildingName = binding.editBuildingName.text.trim().toString().isNotEmpty()
        val checkMemo = binding.editMemo.text.trim().toString().isNotEmpty()
        val checkFloorMax = binding.editFloorMax.text.trim().toString().isNotEmpty()
        val checkFloorMin = binding.editFloorMin.text.trim().toString().isNotEmpty()
        binding.btnNext.isEnabled = checkBuildingName && checkMemo && checkFloorMax && checkFloorMin
    }

    private fun openBuildingCreateStep2() {
        val buildingId = viewModel.buildingCreate1Response.value?.buildingId
        val buildingName = binding.editBuildingName.text.trim().toString()
        val floorMax = binding.editFloorMax.text.trim().toString()
        val floorMin = binding.editFloorMin.text.trim().toString()

        findNavController().navigate(R.id.action_frag_building_create1_to_frag_building_create2,
            bundleOf(
                KEY_BUILDING_ID to buildingId,
                KEY_BUILDING_NAME to buildingName,
                KEY_BUILDING_FLOOR_MAX to floorMax,
                KEY_BUILDING_FLOOR_MIN to floorMin
            ))
    }

}