package com.example.safetymanagement2022.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.FragmentHomeBinding
import com.example.safetymanagement2022.ui.common.MyViewModelFactory

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels { MyViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.homeData.observe(viewLifecycleOwner) { data ->
            binding.rvSafetyIssue.adapter = HomeAdapter().apply {
                submitList(data.issueList)
            }
        }
    }

}