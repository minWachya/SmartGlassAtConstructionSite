package com.example.safetymanagement2022.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.*
import com.example.safetymanagement2022.data.remote.model.request.ConnectIotRequest
import com.example.safetymanagement2022.databinding.FragmentHomeBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.basic_dialog.BasicDialog
import com.example.safetymanagement2022.ui.connect_building.SelectBuildingDialog
import com.example.safetymanagement2022.ui.connect_smart_glass.SelectSmartGlassDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    private var glassId = -1
    private var glassName = ""
    private var buildingId = -1
    private var buildingName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setLayout(USER_ID)
    }

    private fun setLayout(userId: String) {
        viewModel.getHomeResponse(userId)
        viewModel.homeResponse.observe(viewLifecycleOwner) { homeResponse ->
            binding.home = homeResponse
            homeAdapter = HomeAdapter(homeResponse.issueList)
            binding.rvSafetyIssue.adapter = homeAdapter

            // 관리자 모드
            if (homeResponse.admin == KEY_MANAGER) setSpinner(homeResponse.buildingList ?: listOf())
            // 사용자 모드
            else setConnectGlassBtnListener(userId, viewModel.homeResponse.value?.isConnected!!.toInt())
        }
    }

    private fun setSpinner(list: List<String>) {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if (position == 0) homeAdapter.filter.filter("")
                else homeAdapter.filter.filter(list[position])
            }
        }
    }

    private fun setConnectGlassBtnListener(userId: String, isConnected: Int) {
        binding.btnConnectSamrtglass.setOnClickListener {
            if (isConnected == KEY_ENABLE) disConnectIot(userId)
            else setConnectIot(userId)
        }
    }

    private fun setConnectIot(userId: String) {
        // 스마트 글래스 선택 Dialog
        val glassDialog = SelectSmartGlassDialog(requireContext(), userId)
        // 건물 선택 Dialog
        val buildingDialog = SelectBuildingDialog(requireContext(), userId)

        parentFragmentManager.setFragmentResultListener(KEY_DIALOG_GLASS,
            viewLifecycleOwner) { _, bundle ->
            glassId = bundle.get(KEY_DIALOG_GLASS_ID).toString().toInt()
            glassName = bundle.get(KEY_DIALOG_GLASS_NAME).toString()
            buildingDialog.show(parentFragmentManager, "SelectBuildingDialog")
        }
        parentFragmentManager.setFragmentResultListener(KEY_DIALOG_BUILDING,
            viewLifecycleOwner) { _, bundle ->
            buildingId = bundle.get(KEY_DIALOG_BUILDING_ID).toString().toInt()
            buildingName = bundle.get(KEY_DIALOG_BUILDING_NAME).toString()
            // 확인 Dialog
            val confirmDialog = BasicDialog("연결 확인",
                """
                    스마트 글래스: ‘${glassName}’
                    건물명: ‘${buildingName}’
                    로 연결하시겠습니까?
                    """.trimIndent(),
                "", "연결하기")
            confirmDialog.show(parentFragmentManager, "BasicDialog")
        }
        parentFragmentManager.setFragmentResultListener(KEY_DIALOG_BASIC_BTN2_CLICK,
            viewLifecycleOwner) { _, _ ->
            val request = ConnectIotRequest(userId, glassId, buildingId)
            postConnectIot(request)
        }

        glassDialog.show(parentFragmentManager, "SelectSmartGlassDialog")
    }

    private fun postConnectIot(body: ConnectIotRequest) {
        viewModel.postConnectIot(body)
        viewModel.connectIotResponse.observe(viewLifecycleOwner) { response ->
            Log.d("mmm home-connect", "${response.connectMessage}")
            setLayout(body.userId)
        }
    }

    private fun disConnectIot(userId: String) {
        // 연결 종료 확인 Dialog
        val confirmDialog = BasicDialog("연결 종료",
            """
               스마트 글래스: ‘${glassName}’
               건물명: ‘${buildingName}’
               연결을 종료하시겠습니까?
               """.trimIndent(),
            "", "연결 종료하기")
        confirmDialog.show(parentFragmentManager, "BasicDialog")
        parentFragmentManager.setFragmentResultListener(KEY_DIALOG_BASIC_BTN2_CLICK,
            viewLifecycleOwner) { _, _ ->
            viewModel.getDisConnectIot(userId)
            viewModel.disConnectIotResponse.observe(viewLifecycleOwner) { response ->
                Log.d("mmm home-disconnect", "${response.message}")
                setLayout(userId)
            }
        }
    }
}