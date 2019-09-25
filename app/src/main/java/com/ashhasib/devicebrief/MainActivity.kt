package com.ashhasib.devicebrief

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.MemoryFile
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import com.ashhasib.devicebrief.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView


        initComponents()

        setupBottomNav()

    }










    private fun initComponents() {
        val deviceFragment = DeviceFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, deviceFragment)
            .commit()
    }



    private fun setupBottomNav() {

        var fragment = Fragment()

        bottomNav.setOnNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.bottom_nav_system -> {
                    fragment = DeviceFragment()
                }

                R.id.bottom_nav_cpu -> {
                    fragment = CPUFragment()
                }

                R.id.bottom_nav_memory -> {
                    fragment = MemoryFragment()
                }

                R.id.bottom_nav_networks -> {
                    fragment = NetworkFragment()
                }

                R.id.bottom_nav_apps -> {
                    fragment = AppsFragment()
                }
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()


            return@setOnNavigationItemSelectedListener true
        }
    }
}
