package com.example.safetymanagement2022.ui.list_smartglass

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.FragmentListSmartglassBinding
import com.example.safetymanagement2022.ui.glass_create.GlassCreateActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSmartGlassFragment: Fragment() {
    private lateinit var binding: FragmentListSmartglassBinding
    private val viewModel: ListSmartGlassViewModel by viewModels()

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
        val admin = viewModel.listGlassResponse.value?.admin ?: 0
        binding.admin = admin
        binding.viewModel = viewModel

        viewModel.getListGlass("seongmin")
        viewModel.listGlassResponse.observe(viewLifecycleOwner) { data ->
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