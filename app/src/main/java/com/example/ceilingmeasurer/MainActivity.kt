package com.example.ceilingmeasurer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ceilingmeasurer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
        initStartScreen()
    }

    private fun initStartScreen() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, ClientsFragment())
        transaction.commit()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.nav_clients -> {
                    transaction.replace(R.id.main_container, ClientsFragment())
//            transaction.addToBackStack(null)
                    transaction.commit()
                }
                R.id.nav_materials -> {
                    transaction.replace(R.id.main_container, MaterialsFragment())
                    transaction.commit()
                }
                R.id.nav_orders -> {
                    transaction.replace(R.id.main_container, OrdersFragment())
                    transaction.commit()
                }
            }
            true
        }
    }
}