package com.example.safetymanagement2022.ui.building_create

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MAX
import com.example.safetymanagement2022.common.KEY_BUILDING_FLOOR_MIN
import com.example.safetymanagement2022.common.KEY_BUILDING_MEMO
import com.example.safetymanagement2022.common.KEY_BUILDING_NAME
import com.example.safetymanagement2022.databinding.FragmentBuildingCreate2Binding
import com.example.safetymanagement2022.databinding.ItemFloorPlanBinding
import com.example.safetymanagement2022.model.FloorPlanData
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

// 다이얼로그에서 값 받아오기 위한 인터페이스
interface SelectImageInterface {
    fun onSelectedImage(countIndex: Int, bitmap: Bitmap?, itemBinding: ItemFloorPlanBinding)
}

class BuildingCreate2Fragment : Fragment(), SelectImageInterface {
    private lateinit var binding: FragmentBuildingCreate2Binding
    private val viewModel: BuildingCreateViewModel by viewModels { MyViewModelFactory(requireContext()) }

    private var arrImage = ArrayList<FloorPlanData>()
    private var bitmap: Bitmap? = null

//    // 갤러리에서 사진 선택 후 실행
//    private val getFromAlbumResultLauncher = requireActivity().registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val uri = result.data?.data // 선택한 이미지의 주소
//            // 이미지 파일 읽어와서 설정하기
//            if (uri != null) {
//                // 사진 가져오기
//                bitmap = BitmapFactory.decodeStream(requireActivity().contentResolver.openInputStream(uri))
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuildingCreate2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val buildingName = requireArguments().getString(KEY_BUILDING_NAME)
        val memo = requireArguments().getString(KEY_BUILDING_MEMO)
        val floorMax = requireArguments().getString(KEY_BUILDING_FLOOR_MAX)?.toIntOrNull() ?: 0
        val floorMin = requireArguments().getString(KEY_BUILDING_FLOOR_MIN)?.toIntOrNull() ?: 0

        setFloorList(floorMax, floorMin)
        viewModel.setListFloorPlan(arrImage)
        setBackBtnClickListener()

        viewModel.listFloorPlan.observe(viewLifecycleOwner) {
            binding.rvFloorPlan.adapter = BuildingCreateAdapter(viewModel, this@BuildingCreate2Fragment).apply {
                submitList(arrImage)
            }
        }
        viewModel.openButton2Event.observe(viewLifecycleOwner) {
            openBuildingCreateFinish()
        }

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
    override fun onSelectedImage(countIndex: Int, bitmap: Bitmap?, itemBinding: ItemFloorPlanBinding) {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"     // 모든 이미지
//        getFromAlbumResultLauncher.launch(intent)
        startActivityForResult(intent, countIndex)
        itemBinding.ivFloorPlan.setImageResource(
            if(bitmap != null && arrImage[countIndex].image != null) R.drawable.ic_image_on
            else R.drawable.ic_image_off
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (Build.VERSION.SDK_INT >= 19) {
                val uri = data?.data    // 선택한 이미지의 주소
                // 사용자가 이미지를 선택했으면(null이 아니면)
                if (uri != null) {
                    bitmap = BitmapFactory.decodeStream(requireActivity().contentResolver.openInputStream(uri))
                    arrImage[requestCode].image = bitmap
                }
                else bitmap = null
            }
        }
    }

}