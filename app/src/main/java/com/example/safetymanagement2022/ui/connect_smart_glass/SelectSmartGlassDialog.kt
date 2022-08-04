package com.example.safetymanagement2022.ui.connect_smart_glass

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
import com.example.safetymanagement2022.common.KEY_DIALOG_GLASS
import com.example.safetymanagement2022.common.KEY_DIALOG_GLASS_ID
import com.example.safetymanagement2022.common.KEY_DIALOG_GLASS_NAME
import com.example.safetymanagement2022.databinding.DialogSelectSmartGlassBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

class SelectSmartGlassDialog(context: Context) : DialogFragment() {
    private var _binding: DialogSelectSmartGlassBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConnectGlassViewModel by viewModels { MyViewModelFactory(context) }

    private val adapter = ConnectGlassAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogSelectSmartGlassBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 스마트 글래스 목록 연결
        viewModel.glassList.observe(viewLifecycleOwner) { data ->
            binding.rvSmartGlass.adapter = adapter.apply {
                submitList(data.list)
            }
        }

        // 취소 버튼
        binding.btnBack.setOnClickListener {
            dismiss()
        }
        // 다음 버튼
        binding.btnNext.setOnClickListener {
            val glassId = adapter. getSmartGlassId()
            val glassName = adapter.getSmartGlassName()
            setFragmentResult(KEY_DIALOG_GLASS, bundleOf(
                KEY_DIALOG_GLASS_ID to glassId,
                KEY_DIALOG_GLASS_NAME to glassName
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