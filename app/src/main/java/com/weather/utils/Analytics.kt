package com.weather.utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext

object Analytics {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    
    // init at app start
    fun initFirebaseAnalytics(@ApplicationContext context: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    // Used throughout App
    fun logEvents(evetName: String, eventValue: String) {
        if (firebaseAnalytics == null) return
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, eventValue)
        firebaseAnalytics.logEvent(evetName, bundle)
    }
}