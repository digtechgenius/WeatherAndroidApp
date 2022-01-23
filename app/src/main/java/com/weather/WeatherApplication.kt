package com.weather

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.weather.utils.Analytics
import com.weather.di.AppModule
import com.weather.utils.AppConstants
import com.weather.utils.AppConstants.FIREBASE_CONFIG_UPDATE
import com.weather.utils.RemoteConfigs

@HiltAndroidApp
class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        FirebaseApp.initializeApp(applicationContext)
        Analytics.initFirebaseAnalytics(applicationContext)
        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        // set in-app defaults
        val remoteConfigDefaults: MutableMap<String, Any> = HashMap()
        remoteConfigDefaults[AppConstants.KEY_UPDATE_REQUIRED] = RemoteConfigs.isUpdateReq
        remoteConfigDefaults[AppConstants.KEY_CURRENT_VERSION] = RemoteConfigs.updateVersion
        remoteConfigDefaults[AppConstants.KEY_UPDATE_URL] = RemoteConfigs.updateURL


        firebaseRemoteConfig.setDefaultsAsync(remoteConfigDefaults)
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(FIREBASE_CONFIG_UPDATE)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        firebaseRemoteConfig.fetchAndActivate() // fetch every day
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("remote config is fetched.")
                    val remoteConfig = AppModule.provideFireBaseConfig()
                    RemoteConfigs.isUpdateReq = remoteConfig.getBoolean(AppConstants.KEY_UPDATE_REQUIRED)
                    RemoteConfigs.updateVersion = remoteConfig.getString(AppConstants.KEY_CURRENT_VERSION)
                    RemoteConfigs.updateURL = remoteConfig.getString(AppConstants.KEY_UPDATE_URL)

                }
            }
    }

}