package com.example.safetymanagement2022.ui.building_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.addTextChangedListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.databinding.FragmentBuildingCreate1Binding

class BuildingCreate1Fragment : Fragment() {
    private lateinit var binding: FragmentBuildingCreate1Binding

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

        setButtonEnableListener()
        setButtonClickListener()

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

    private fun setButtonClickListener() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_building_create_1_to_building_create_2)
        }
    }

}