package com.example.todolist

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.utils.UiActivity
import com.example.todolist.utils.showSystemUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UiActivity {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLightStatusBar(isDarkTheme())
        initNavController()
    }


    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun showProgress(boolean: Boolean) {
        if (boolean) binding.progressBar.visibility =
            View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private fun isDarkTheme(): Boolean {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }

    private fun setLightStatusBar(value: Boolean) {
        when (value) {
            false -> window.showSystemUI(true)
            true -> window.showSystemUI(false)
        }
    }
}