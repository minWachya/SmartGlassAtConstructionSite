package com.example.safetymanagement2022.ui.list.glasscreate

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.databinding.FragmentGlassCreateBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.custom.dialog_basic.BasicDialog
import com.example.safetymanagement2022.ui.common.BasicDialogReturnValueInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GlassCreateFragment : BaseFragment<FragmentGlassCreateBinding>(R.layout.fragment_glass_create),
    BasicDialogReturnValueInterface {
    private val viewModel: GlassCreateViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setBtnEnableListener()
        setFinishBtnListener()
        setBackBtnClickListener()

        viewModel.glassCreateResponse.observe(viewLifecycleOwner) { data ->
            val glassName = binding.editGlassName.text.toString()
            BasicDialog("스마트 글래스 추가 완료", "‘$glassName’가 정상적으로  추가되었습니다.",
                "", "확인").show(parentFragmentManager, "CustomDialog")
            findNavController().popBackStack()
        }
    }

    private fun setBtnEnableListener() {
        binding.editGlassName.addTextChangedListener { text ->
            binding.btnFinish.isEnabled = text?.trim().toString().isNotEmpty()
        }
    }

    private fun setFinishBtnListener() {
        binding.btnFinish.setOnClickListener {
            val glassName = binding.editGlassName.text.toString()
            viewModel.postGlassCreate(viewModel.getUserId(), GlassCreateRequest(glassName))
        }
    }

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onClickBtn1() { }
    override fun onClickBtn2() { findNavController().popBackStack() }

}