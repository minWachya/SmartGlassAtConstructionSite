package com.example.safetymanagement2022.ui.custom.dialog_connect_building

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.common.KEY_DIALOG_BUILDING
import com.example.safetymanagement2022.common.KEY_DIALOG_BUILDING_ID
import com.example.safetymanagement2022.common.KEY_DIALOG_BUILDING_NAME
import com.example.safetymanagement2022.databinding.DialogSelectBuildingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectBuildingDialog(context: Context, val userId: String) : DialogFragment() {
    private var _binding: DialogSelectBuildingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConnectBuildingViewModel by viewModels()

    private val adapter = ConnectBuildingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogSelectBuildingBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 빌딩 목록 연결
        viewModel.getConnectBuilding(userId)
        viewModel.buildingList.observe(viewLifecycleOwner) { data ->
            binding.rvBuilding.adapter = adapter.apply {
                submitList(data.list)
            }
        }

        // 취소 버튼
        binding.btnBack.setOnClickListener {
            dismiss()
        }
        // 다음 버튼
        binding.btnOk.setOnClickListener {
            setFragmentResult(KEY_DIALOG_BUILDING, bundleOf(
                KEY_DIALOG_BUILDING_ID to adapter.getBuildingId(),
                KEY_DIALOG_BUILDING_NAME to adapter.getBuildingName()
            ))
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}