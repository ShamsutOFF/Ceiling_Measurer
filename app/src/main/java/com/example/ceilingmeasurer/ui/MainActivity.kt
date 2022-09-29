package com.example.ceilingmeasurer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.ActivityMainBinding
import com.example.ceilingmeasurer.ui.clientsList.ClientsListFragment
import com.example.ceilingmeasurer.ui.materialsList.MaterialsListFragment
import com.example.ceilingmeasurer.utils.IOnBackPressed

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val FRAGMENT_CLIENTS = "clients"
        const val FRAGMENT_MATERIALS = "materials"
        const val FRAGMENT_ORDERS = "orders"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CeilingMeasurer)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
        if (supportFragmentManager.findFragmentById(R.id.main_container) == null) {
            attachFragment(ClientsListFragment(), FRAGMENT_CLIENTS)
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_clients -> attachFragment(ClientsListFragment(), FRAGMENT_CLIENTS)
                R.id.nav_materials -> attachFragment(MaterialsListFragment(), FRAGMENT_MATERIALS)
                R.id.nav_orders -> attachFragment(OrdersFragment(), FRAGMENT_ORDERS)
            }
            true
        }
    }

    private fun attachFragment(fragment: Fragment, tag: String) {
        val tempFragment = supportFragmentManager.findFragmentByTag(tag)
        if (tempFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, tag)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, tempFragment, tag)
                .commit()
        }
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.main_container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            //nothing
        }
    }
}