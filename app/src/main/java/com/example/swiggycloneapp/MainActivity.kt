package com.example.swiggycloneapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.swiggycloneapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            var textViewId: TextView = binding.tvHome
            when (destination.id){
                R.id.homeFragment -> {
                    window.statusBarColor = getColor(R.color.white)
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
                    textViewId = binding.tvHome
                    binding.viewHome.visibility = View.VISIBLE
                    binding.viewFood.visibility = View.GONE
                    binding.viewInstamart.visibility = View.GONE
                    binding.viewDineout.visibility = View.GONE
                    binding.viewGenie.visibility = View.GONE
                    binding.tvFood.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvFood.typeface, Typeface.NORMAL)
                    binding.tvInstamart.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvInstamart.typeface, Typeface.NORMAL)
                    binding.tvDineout.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvDineout.typeface, Typeface.NORMAL)
                    binding.tvGenie.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvGenie.typeface, Typeface.NORMAL)
                }
                R.id.foodFragment -> {
                    window.statusBarColor = getColor(R.color.white)
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
                    textViewId = binding.tvFood
                    binding.viewHome.visibility = View.GONE
                    binding.viewFood.visibility = View.VISIBLE
                    binding.viewInstamart.visibility = View.GONE
                    binding.viewDineout.visibility = View.GONE
                    binding.viewGenie.visibility = View.GONE
                    binding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvHome.typeface, Typeface.NORMAL)
                    binding.tvInstamart.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvInstamart.typeface, Typeface.NORMAL)
                    binding.tvDineout.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvDineout.typeface, Typeface.NORMAL)
                    binding.tvGenie.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvGenie.typeface, Typeface.NORMAL)
                }
                R.id.instamartFragment -> {
                    window.statusBarColor = getColor(R.color.bg_pink)
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
                    textViewId = binding.tvInstamart
                    binding.viewHome.visibility = View.GONE
                    binding.viewFood.visibility = View.GONE
                    binding.viewInstamart.visibility = View.VISIBLE
                    binding.viewDineout.visibility = View.GONE
                    binding.viewGenie.visibility = View.GONE
                    binding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvHome.typeface, Typeface.NORMAL)
                    binding.tvFood.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvFood.typeface, Typeface.NORMAL)
                    binding.tvDineout.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvDineout.typeface, Typeface.NORMAL)
                    binding.tvGenie.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvGenie.typeface, Typeface.NORMAL)
                }
                R.id.dineoutFragment -> {
                    window.statusBarColor = getColor(R.color.black)
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
                    textViewId = binding.tvDineout
                    binding.viewHome.visibility = View.GONE
                    binding.viewFood.visibility = View.GONE
                    binding.viewInstamart.visibility = View.GONE
                    binding.viewDineout.visibility = View.VISIBLE
                    binding.viewGenie.visibility = View.GONE
                    binding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvHome.typeface, Typeface.NORMAL)
                    binding.tvFood.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvFood.typeface, Typeface.NORMAL)
                    binding.tvInstamart.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvInstamart.typeface, Typeface.NORMAL)
                    binding.tvGenie.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvGenie.typeface, Typeface.NORMAL)
                }
                R.id.genieFragment -> {
                    window.statusBarColor = getColor(R.color.bg_violet)
                    window.decorView.windowInsetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
                    textViewId = binding.tvGenie
                    binding.viewHome.visibility = View.GONE
                    binding.viewFood.visibility = View.GONE
                    binding.viewInstamart.visibility = View.GONE
                    binding.viewDineout.visibility = View.GONE
                    binding.viewGenie.visibility = View.VISIBLE
                    binding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvHome.typeface, Typeface.NORMAL)
                    binding.tvFood.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvFood.typeface, Typeface.NORMAL)
                    binding.tvInstamart.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvInstamart.typeface, Typeface.NORMAL)
                    binding.tvDineout.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))
                    textViewId.typeface = Typeface.create(binding.tvDineout.typeface, Typeface.NORMAL)
                }
            }

            textViewId.setTextColor(Color.BLACK)
            textViewId.typeface = Typeface.create(binding.tvHome.typeface, Typeface.BOLD)
        }
    }
}