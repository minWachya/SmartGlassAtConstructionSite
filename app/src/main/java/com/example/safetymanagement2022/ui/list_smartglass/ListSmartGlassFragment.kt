package com.example.safetymanagement2022.ui.list_smartglass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.safetymanagement2022.databinding.FragmentListSmartglassBinding

class ListSmartGlassFragment: Fragment() {
    private lateinit var binding: FragmentListSmartglassBinding

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
        // TODO: binding viewLifecycleOwner 할당 해줘야 함

    }

}