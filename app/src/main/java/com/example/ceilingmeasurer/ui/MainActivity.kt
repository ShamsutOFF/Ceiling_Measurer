package com.example.ceilingmeasurer.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.ActivityMainBinding
import com.example.ceilingmeasurer.ui.clientsList.ClientsListFragment
import com.example.ceilingmeasurer.utils.IOnBackPressed
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAppBar()
        initBottomNavigation()
        if (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_naw_drawer) == null) {
            attachFragment(ClientsListFragment(), FRAGMENT_CLIENTS)
        }
    }

    private fun initAppBar(){
        setSupportActionBar(binding.appBarNawDrawer.toolbar)

        binding.appBarNawDrawer.fab.setOnClickListener { view ->
            Timber.d("FAB CLICK!")
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_naw_drawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.naw_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_naw_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_clients -> attachFragment(ClientsListFragment(), FRAGMENT_CLIENTS)
                R.id.nav_materials -> attachFragment(MaterialsFragment(), FRAGMENT_MATERIALS)
                R.id.nav_orders -> attachFragment(OrdersFragment(), FRAGMENT_ORDERS)
            }
            true
        }
    }

    private fun attachFragment(fragment: Fragment, tag: String) {
        val tempFragment = supportFragmentManager.findFragmentByTag(tag)
        if (tempFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_naw_drawer, fragment, tag)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_naw_drawer, tempFragment, tag)
                .commit()
        }
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_naw_drawer)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            //nothing
        }
    }

    companion object {
        const val FRAGMENT_CLIENTS = "clients"
        const val FRAGMENT_MATERIALS = "materials"
        const val FRAGMENT_ORDERS = "orders"
    }
}