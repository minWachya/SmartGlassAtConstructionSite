package com.example.safetymanagement2022

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.safetymanagement2022.databinding.ActivityMainBinding
import com.example.safetymanagement2022.ui.account.AccountViewModel
import com.example.safetymanagement2022.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val loginViewModel: AccountViewModel by viewModels()
    private lateinit var navController: NavController
    lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigation()
        goToNextPage(loginViewModel.getUserId() != "")
    }

    private fun initNavigation() {
        binding.navigationMain.itemIconTintList = null    // Theme 의 color 로 아이콘 색 표시 X, drawable 그대로 사용
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_main) as NavHostFragment
        navController = navHostFragment.navController
        navGraph = navController.navInflater.inflate(R.navigation.nav_main)
        navController.let {
            binding.navigationMain.setupWithNavController(it)
            it.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.navigation_list ||
                    destination.id == R.id.navigation_home ||
                    destination.id == R.id.navigation_setting
                ) {
                    binding.navigationMain.visibility = View.VISIBLE
                } else {
                    binding.navigationMain.visibility = View.GONE
                }
            }
        }
    }

    private fun goToNextPage(login: Boolean) {
        if (login) {
            navGraph.setStartDestination(R.id.navigation_home)
        } else {
            navGraph.setStartDestination(R.id.frag_login)
        }
        navController.graph = navGraph
    }

}