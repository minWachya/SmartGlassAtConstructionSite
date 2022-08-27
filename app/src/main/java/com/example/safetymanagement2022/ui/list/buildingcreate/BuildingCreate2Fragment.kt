package com.example.safetymanagement2022.ui.list.buildingcreate

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MAX
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MIN
import com.example.safetymanagement2022.common.KEY_BUILDING_MEMO
import com.example.safetymanagement2022.common.KEY_BUILDING_NAME
import com.example.safetymanagement2022.databinding.FragmentBuildingCreate2Binding
import com.example.safetymanagement2022.model.FloorPlanData
import com.example.safetymanagement2022.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

// 다이얼로그에서 값 받아오기 위한 인터페이스
interface SelectImageInterface {
    fun onSelectedImage(countIndex: Int)
}

@AndroidEntryPoint
class BuildingCreate2Fragment : BaseFragment<FragmentBuildingCreate2Binding>(R.layout.fragment_building_create_2),
    SelectImageInterface {
    private val viewModel: BuildingCreateViewModel by viewModels()

    private var arrImage = ArrayList<FloorPlanData>()
    private var bitmap: Bitmap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val buildingName = requireArguments().getString(KEY_BUILDING_NAME)
        val memo = requireArguments().getString(KEY_BUILDING_MEMO)
        val floorMax = requireArguments().getString(KEY_BUILDING_FLOOR_MAX)?.toIntOrNull() ?: 0
        val floorMin = requireArguments().getString(KEY_BUILDING_FLOOR_MIN)?.toIntOrNull() ?: 0

        setFloorList(floorMax, floorMin)
        viewModel.setListFloorPlan(arrImage)
        setBackBtnClickListener()
        checkBtnEnable()

        viewModel.listFloorPlan.observe(viewLifecycleOwner) {
            binding.rvFloorPlan.adapter = BuildingCreateAdapter(viewModel, this@BuildingCreate2Fragment).apply {
                submitList(arrImage)
            }
        }
        viewModel.openButton2Event.observe(viewLifecycleOwner) {
            openBuildingCreateFinish()
        }

    }

    private fun checkBtnEnable() {
        for(item in arrImage)
            if(item.image == null) {
                binding.btnNext.isEnabled = false
                return
            }
        binding.btnNext.isEnabled = true
    }

    private fun openBuildingCreateFinish() {
        Toast.makeText(context, "끝!!", Toast.LENGTH_LONG).show()
    }

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setFloorList(floorMax: Int, floorMin: Int) {
        for (i in floorMax downTo 1) arrImage.add(FloorPlanData("지상 " + i + "층",null))
        for (i in 1..floorMin) arrImage.add(FloorPlanData("지하 " + i + "층", null))
    }

    //  Adapter 에서 값 받고나서 할 작업들
    override fun onSelectedImage(countIndex: Int) {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"     // 모든 이미지
        startActivityForResult(intent, countIndex)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (Build.VERSION.SDK_INT >= 19) {
                val uri = data?.data    // 선택한 이미지의 주소
                if (uri != null) {
                    bitmap = BitmapFactory.decodeStream(requireActivity().contentResolver.openInputStream(uri))
                    arrImage[requestCode].image = bitmap
                    // '완료' 버튼 활성화 체크
                    checkBtnEnable()
                    // 이미지 색 변경
                    (binding.rvFloorPlan.adapter as BuildingCreateAdapter)
                        .arrImgView[requestCode]
                        .setImageResource(R.drawable.ic_image_on)
                }
                else bitmap = null
            }
        }
    }

}