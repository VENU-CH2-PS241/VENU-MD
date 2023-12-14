package com.capstone.venu.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivityMainBinding
import com.capstone.venu.ui.checker.CheckerFragment
import com.capstone.venu.ui.home.HomeFragment
import com.capstone.venu.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigationView()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        //setOnItemSelected error

    }

    private fun setupBottomNavigationView() {
        binding.botNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bn_home -> replaceFragment(HomeFragment())
                R.id.bn_checker -> replaceFragment(CheckerFragment())
                R.id.bn_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.botNavView,fragment)
        fragmentTransaction.commit()
    }
}