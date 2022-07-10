package com.example.safetymanagement2022.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.safetymanagement2022.databinding.FragmentListBinding
import com.example.safetymanagement2022.ui.list_building.ListBuildingFragment
import com.example.safetymanagement2022.ui.list_smartglass.ListSmartGlassFragment
import com.google.android.material.tabs.TabLayoutMediator

class ListFragment: Fragment() {
    private lateinit var binding: FragmentListBinding
    private val tabElement = arrayListOf("건물", "스마트글래스")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setTabLayout()
    }

    private fun setTabLayout() {
        // 어댑터 생성
        val adapter = ViewpagerAdapter(this@ListFragment)
        // 프레그먼트, 탭 타이틀 넣기(프레그먼트 하나로 통일~)
        adapter.addFragment(ListBuildingFragment())     // 건물
        adapter.addFragment(ListSmartGlassFragment())   // 스마트글래스
        binding.feedViewPager.adapter = adapter
        // 탭레이아웃에 뷰 페이저 달기
        TabLayoutMediator(binding.tabTabLayout, binding.feedViewPager) { tab, position ->
            tab.text = tabElement[position]
        }.attach()
    }

    // Viewpager2 어댑터
    inner class ViewpagerAdapter(fragment: Fragment) :  FragmentStateAdapter(fragment)  {
        // 프레그먼트 배열
        private val fragmentList = ArrayList<Fragment>()
        // 프레그먼트, 탭 타이틀 추가
        fun addFragment(fragment: Fragment) = fragmentList.add(fragment)
        // 총 프레그먼트 갯수 반환
        override fun getItemCount(): Int = fragmentList.size
        // position 번째 프레그먼트 반환
        override fun createFragment(position: Int): Fragment = fragmentList[position]
    }
}