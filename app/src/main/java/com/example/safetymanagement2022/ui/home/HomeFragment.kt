package com.example.safetymanagement2022.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.safetymanagement2022.databinding.FragmentHomeBinding
import com.example.safetymanagement2022.model.SafetyIssue
import com.example.safetymanagement2022.ui.common.MyViewModelFactory
import com.example.safetymanagement2022.ui.connect_smart_glass.SelectSmartGlassDialog

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
        binding.home = viewModel.homeData.value
        val listIssue = (viewModel.homeData.value?.issueList ?: arrayListOf()) as ArrayList<SafetyIssue>
        val listSpinner = viewModel.homeData.value?.buildingList ?: listOf()
        val adapter = HomeAdapter(listIssue)

        viewModel.homeData.observe(viewLifecycleOwner) { data ->
            binding.rvSafetyIssue.adapter = adapter
        }

        // 관리자 모드
        setSpinner(adapter, listSpinner)
        setConnectGlassBtn()

    }

    private fun setSpinner(adapter: HomeAdapter, list: List<String>) {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == 0) adapter.filter.filter("")
                else adapter.filter.filter(list[position])
            }
        }
    }

    private fun setConnectGlassBtn() {
        binding.btnConnectSamrtglass.setOnClickListener {
            // 스마트 글래스 선택 > 건물 선택
            SelectSmartGlassDialog(requireContext()).show(parentFragmentManager, "SelectSmartGlassDialog")
        }
    }

}