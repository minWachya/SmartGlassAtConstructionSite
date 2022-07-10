package com.example.safetymanagement2022.ui.list_building

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.FragmentListBuildingBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

class ListBuildingFragment: Fragment() {
    private lateinit var binding: FragmentListBuildingBinding
    private val viewModel: ListBuildingViewModel by viewModels { MyViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBuildingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.listBuildingData.observe(viewLifecycleOwner) { data ->
            binding.rvListBuilding.adapter = ListBuildingAdapter().apply {
                submitList(data.buildingList)
            }
        }
    }

}