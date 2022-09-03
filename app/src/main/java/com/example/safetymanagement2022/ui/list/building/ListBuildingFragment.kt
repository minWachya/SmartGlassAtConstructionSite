package com.example.safetymanagement2022.ui.list.building

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.*
import com.example.safetymanagement2022.databinding.FragmentListBuildingBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.list.buildingdetail.BuildingDetailActivity
import com.example.safetymanagement2022.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListBuildingFragment: BaseFragment<FragmentListBuildingBinding>(R.layout.fragment_list_building)  {
    private val viewModel: ListBuildingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getListBuilding(USER_ID)
        viewModel.listBuildingResponse.observe(viewLifecycleOwner) { data ->
            binding.rvListBuilding.adapter = ListBuildingAdapter(viewModel).apply {
                submitList(data.buildingList)
            }
            binding.viewModel = viewModel
            binding.admin = viewModel.listBuildingResponse.value?.admin ?: 0
        }
        viewModel.openCreateBuildingEvent.observe(viewLifecycleOwner, EventObserver {
            openCreateBuilding()
        })
        viewModel.openBuildingDetailEvent.observe(viewLifecycleOwner, EventObserver {
            openBuildingDetail(it)
        })
    }

    private fun openCreateBuilding() {
        findNavController().navigate(R.id.action_navigation_list_to_frag_building_create1)
    }

    private fun openBuildingDetail(buildingId: String) {
        val intent = Intent(context, BuildingDetailActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("buildingId", buildingId)
        startActivity(intent)
    }
}