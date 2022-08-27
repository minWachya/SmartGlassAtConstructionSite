package com.example.safetymanagement2022.ui.list.buildingdetail

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.safetymanagement2022.databinding.DialogSelectFloorBinding
import java.lang.Integer.max


class SelectFloorDialog(private val minFloor: Int, private val maxFloor: Int) : DialogFragment() {
    private var _binding: DialogSelectFloorBinding? = null
    private val binding get() = _binding!!

    private val floorArr = ArrayList<String>()
    private val minMaxArr = arrayListOf("지하", "지상")

    private lateinit var mCallback: SelectedFloorInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogSelectFloorBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 층수 배열 생성
        createMinMaxArr()
        // 지상/지하 피커 설정
        setMinMaxPicker()
        // 층수 피커 설정
        setFloorPicker(0)

        // '선택완료' 버튼 누르면 층수 정보 Activity로 전달
        binding.btnOk.setOnClickListener {
            val minMax = binding.minMaxPicker.value
            val floor = binding.floorPicker.value
            val floorText = minMaxArr[minMax] + " " + floorArr[floor]
            mCallback.onSelectedFloor(floorText, minMax, floor)
            dismiss()
        }

        return view
    }

    private fun createMinMaxArr() {
        val max = max(minFloor, maxFloor) + 1
        for (i in 1 until max) floorArr.add("${i}층")
    }

    private fun setMinMaxPicker() {
        binding.minMaxPicker.let {
            it.minValue = 0
            it.maxValue = 1
            it.wrapSelectorWheel = true
            it.displayedValues = minMaxArr.toTypedArray()
            it.setOnValueChangedListener { picker, oldVal, newVal ->
                if (newVal == 0) setFloorPicker(minFloor-1)
                else setFloorPicker(maxFloor-1)
            }
        }
    }

    private fun setFloorPicker(floorSize: Int) {
        // max 인 층 보여주고 size 만 조절
        binding.floorPicker.let {
            it.minValue = 0
            it.displayedValues = floorArr.toTypedArray()
            it.maxValue = floorSize
            it.wrapSelectorWheel = true
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = activity as SelectedFloorInterface
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}