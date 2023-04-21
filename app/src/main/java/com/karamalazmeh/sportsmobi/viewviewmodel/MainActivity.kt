package com.karamalazmeh.sportsmobi.viewviewmodel

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.karamalazmeh.sportsmobi.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // implement Timber
        Timber.plant(Timber.DebugTree())
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // add action bar implementation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // bind action bar with nav controller
        setupActionBarWithNavController(navController)
    }


    // menu implementation
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navController.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Support back button
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}