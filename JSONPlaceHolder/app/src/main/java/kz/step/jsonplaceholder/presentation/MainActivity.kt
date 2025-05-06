package kz.step.jsonplaceholder.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.presentation.extensions.lazyView

class MainActivity : AppCompatActivity() {

    private val bnvMain: BottomNavigationView by lazyView(R.id.bnv_main)

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
    }

    private fun initNavigation() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        navController = navHostFragment.navController

        bnvMain.setupWithNavController(navController)
    }

}