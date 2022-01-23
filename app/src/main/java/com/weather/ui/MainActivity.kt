package com.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weather.R
import com.weather.utils.ForceUpgrade
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        upgradeChecker()
    }

    private fun upgradeChecker() {
        val forceUpgrade = ForceUpgrade(this)
        val upgradeRequired: Boolean = ForceUpgrade(this).check() // Check for force upgrade
        if (upgradeRequired) {
            forceUpgrade.onUpdateNeeded()
        }
    }
}