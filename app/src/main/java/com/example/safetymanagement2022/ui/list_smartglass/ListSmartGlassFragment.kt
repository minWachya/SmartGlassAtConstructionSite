package com.example.safetymanagement2022.ui.list_smartglass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.FragmentListSmartglassBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

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

        viewModel.listSmartGlassData.observe(viewLifecycleOwner) { data ->
            binding.rvListSmartglass.adapter = ListSmartGlassAdapter().apply {
                submitList(data.glassList)
            }
        }
    }

}