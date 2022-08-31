package com.example.ceilingmeasurer

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.ceilingmeasurer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavDrawer()
        initNavItemListener()
    }

    private fun initNavDrawer() {
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.mainDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initNavItemListener() {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_clients -> {
                    Toast.makeText(this, "R.id.nav_clients", Toast.LENGTH_LONG).show()
                    binding.mainDrawerLayout.close()
                    true
                }
                R.id.nav_materials -> {
                    Toast.makeText(this, "R.id.nav_materials", Toast.LENGTH_LONG).show()
                    binding.mainDrawerLayout.close()
                    true
                }
                R.id.nav_orders -> {
                    Toast.makeText(this, "R.id.nav_orders", Toast.LENGTH_LONG).show()
                    binding.mainDrawerLayout.close()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) true
        else super.onOptionsItemSelected(item)
    }
}