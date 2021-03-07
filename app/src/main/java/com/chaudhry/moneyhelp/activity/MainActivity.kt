package com.chaudhry.moneyhelp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.fragment.DailyProfitFragment
import com.chaudhry.moneyhelp.fragment.HomeFragment
import com.chaudhry.moneyhelp.fragment.MonthlyProfitFragment
import com.chaudhry.moneyhelp.fragment.ProfileFragment
import com.chaudhry.moneyhelp.helper.BottomNavigationBehavior
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame_container) as NavHostFragment

        navController=navHostFragment.findNavController()
        NavigationUI.setupWithNavController(bottomNavigation,navController)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}