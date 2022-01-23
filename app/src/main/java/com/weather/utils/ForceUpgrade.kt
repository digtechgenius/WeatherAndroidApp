package com.weather.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import timber.log.Timber

/*
Utility Class created to force upgrade user
 */
class ForceUpgrade(private val context: Context) {

    //Check for Force Upgrade required
    fun check(): Boolean {
        if (RemoteConfigs.isUpdateReq) {
            val appVersion = getAppVersion(context)
            return (!TextUtils.equals(RemoteConfigs.updateVersion, appVersion))
        }
        return false
    }

    private fun getAppVersion(context: Context): String {
        var result = ""
        try {
            result = context.packageManager
                .getPackageInfo(context.packageName, 0).versionName
            result = result.replace("[a-zA-Z]|-".toRegex(), "")
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e.message)
        }
        return result
    }

    fun onUpdateNeeded() {

        val dialog: AlertDialog = AlertDialog.Builder(context)
            .setTitle("New version available")
            .setMessage("Please, update app to new version to continue reposting.")
            .setPositiveButton(
                "Update"
            ) { _, _ -> redirectStore(RemoteConfigs.updateURL) }
            .setNegativeButton(
                "No, thanks"
            ) { _, _ -> (context as Activity).finish() }.create()
        dialog.show()
    }



    private fun redirectStore(updateUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}