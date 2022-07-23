package com.example.safetymanagement2022.ui.connect_building

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.DialogSelectBuildingBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

class SelectBuildingDialog(context: Context, val glassId : String) : DialogFragment() {
    private var _binding: DialogSelectBuildingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConnectBuildingViewModel by viewModels { MyViewModelFactory(context) }

    private val adapter = ConnectBuildingAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogSelectBuildingBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 스마트 글래스 목록 연결
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
            val buildingId = getBuildingId()
            dismiss()
        }

        return view
    }

    private fun getBuildingId(): String = adapter.getBuildingId()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}