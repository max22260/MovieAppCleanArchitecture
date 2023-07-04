package com.nagy.movieapp.common.presentation

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import androidx.work.*
import com.nagy.movieapp.R
import com.nagy.movieapp.common.worker.ClearDatabaseWorker
import com.nagy.movieapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return@lazy navHostFragment.navController
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf( R.id.findMovieFragment))
    }

    companion object {
        const val WORK_MANAGER_TAG = "WORK_MANAGER_TAG"
        const val REPEAT_INTERVAL = 2L
        const val STARTING_DELAY_TIME = 1L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createWorker()
        setupActionBar()
    }

    private fun createWorker() {

        val workManager = WorkManager.getInstance(application.applicationContext!!)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(false).build()


        val work = PeriodicWorkRequestBuilder<ClearDatabaseWorker>(
            REPEAT_INTERVAL, TimeUnit.MINUTES
        ).setConstraints(constraints)
            .setInitialDelay(STARTING_DELAY_TIME, TimeUnit.MINUTES)
            .addTag(WORK_MANAGER_TAG).build()


        workManager.enqueue(work)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupActionBar() {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun cancelWorker(
        application: Application, tag: String
    ) {
        WorkManager.getInstance(application.applicationContext!!).cancelAllWorkByTag(tag)
    }
}