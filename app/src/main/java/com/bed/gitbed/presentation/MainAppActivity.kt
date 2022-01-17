package com.bed.gitbed.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bed.gitbed.R
import com.bed.gitbed.databinding.MainAppActivityBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainAppActivity : AppCompatActivity() {

    private lateinit var binding: MainAppActivityBinding
    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = MainAppActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigationController()
        setAppBar()
        setNavigationBar()
    }

    private fun setNavigationController() {
        val navigationContainerFragment = supportFragmentManager
            .findFragmentById(R.id.navigation_container) as NavHostFragment

        navigationController = navigationContainerFragment.navController
        binding.bottomNavMain.setupWithNavController(navigationController)
    }

    private fun setAppBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.auth_fragment,
                R.id.public_fragment,
                R.id.private_fragment
            )
        )
        binding.toolbarApp.setupWithNavController(navigationController, appBarConfiguration)
    }

    private fun setNavigationBar() {
        navigationController.addOnDestinationChangedListener {_, destination, _ ->
            visibilityGoBackInToolBar(destination.id)
            visibilityNavBarAndToolBar(destination.id)
        }
    }

    private fun visibilityGoBackInToolBar(destination: Int) {
        val isTopLevelDestination = appBarConfiguration.topLevelDestinations.contains(destination)

        if (!isTopLevelDestination) binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
    }

    private fun visibilityNavBarAndToolBar(destination: Int) {
        val visibility = when (destination) {
            R.id.auth_fragment -> GONE
            R.id.detail_fragment -> GONE
            else -> VISIBLE
        }

        with (binding) {
            bottomNavMain.visibility = visibility
            binding.toolbarApp.visibility = visibility
        }
    }

    companion object {
        private const val GONE = View.GONE
        private const val VISIBLE = View.VISIBLE
    }
}