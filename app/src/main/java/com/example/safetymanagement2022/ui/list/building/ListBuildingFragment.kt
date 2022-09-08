package com.example.safetymanagement2022.ui.list.building

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.*
import com.example.safetymanagement2022.databinding.FragmentListBuildingBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListBuildingFragment: BaseFragment<FragmentListBuildingBinding>(R.layout.fragment_list_building)  {
    private val viewModel: ListBuildingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getListBuilding(viewModel.getUserId())
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

    private fun openBuildingDetail(buildingId: Int) {
        findNavController().navigate(R.id.action_navigation_list_to_frag_building_detail, bundleOf(
            KEY_BUILDING_DETAIL_ID to buildingId
        ))
    }
}