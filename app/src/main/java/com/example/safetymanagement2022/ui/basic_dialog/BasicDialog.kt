package com.example.safetymanagement2022.ui.basic_dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.safetymanagement2022.databinding.DialogBasicBinding
import com.example.safetymanagement2022.ui.common.BasicDialogReturnValueInterface

class BasicDialog (val title: String, val content: String, val btn1: String, val btn2: String) : DialogFragment() {
    private var _binding: DialogBasicBinding? = null
    private val binding get() = _binding!!

    private var mCallback: BasicDialogReturnValueInterface? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogBasicBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 제목, 내용 설정
        binding.customTvTitle.text = title
        binding.customTvContent.text = content
        binding.customTvBtn1.text = btn1
        binding.customTvBtn2.text = btn2

        // 버튼1 클릭: 이전
        binding.customTvBtn1.setOnClickListener {
            mCallback?.onClickBtn1()
            dismiss()
        }
        // 버튼2 클릭: 완료
        binding.customTvBtn2.setOnClickListener {
            mCallback?.onClickBtn2()
            dismiss()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = try {
            activity as BasicDialogReturnValueInterface
        } catch (e: ClassCastException) {
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}