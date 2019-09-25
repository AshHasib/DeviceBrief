package com.ashhasib.devicebrief

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView


        setupBottomNav()

    }



    private fun setupBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.bottom_nav_system -> {

                }

                R.id.bottom_nav_cpu -> {

                }

                R.id.bottom_nav_memory -> {

                }

                R.id.bottom_nav_networks -> {

                }

                R.id.bottom_nav_apps -> {

                }
            }


            return@setOnNavigationItemSelectedListener true
        }
    }
}
