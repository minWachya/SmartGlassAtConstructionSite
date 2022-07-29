package com.example.safetymanagement2022.ui.list_smartglass

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.FragmentListSmartglassBinding
import com.example.safetymanagement2022.ui.building_create.BuildingCreateActivity
import com.example.safetymanagement2022.ui.common.MyViewModelFactory
import com.example.safetymanagement2022.ui.glass_create.GlassCreateActivity

class ListSmartGlassFragment: Fragment() {
    private lateinit var binding: FragmentListSmartglassBinding
    private val viewModel: ListSmartGlassViewModel by viewModels { MyViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListSmartglassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val admin = viewModel.listSmartGlassData.value?.admin ?: 0
        binding.admin = admin
        binding.viewModel = viewModel

        viewModel.listSmartGlassData.observe(viewLifecycleOwner) { data ->
            binding.rvListSmartglass.adapter = ListSmartGlassAdapter(admin).apply {
                submitList(data.glassList)
            }
        }
        viewModel.openCreateGlassEvent.observe(viewLifecycleOwner) {
            openCreateGlass()
        }
    }

    private fun openCreateGlass() {
        val intent = Intent(context, GlassCreateActivity::class.java)
        startActivity(intent)
    }

}