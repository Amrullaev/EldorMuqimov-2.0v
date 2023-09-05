package com.example.eldormuqimov20.responseUtils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri


fun openTelegram(activity: Activity) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/amrullaev_blog"))
    intent.setPackage("org.telegram.plus")
    try {
        activity.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        activity.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/amrullaev_blog"))
        )
    }
}

fun openInstagram(activity: Activity) {
    val uri = Uri.parse("https://www.instagram.com/uchqun_amrullaev/")
    val likeIng = Intent(Intent.ACTION_VIEW, uri)
    likeIng.setPackage("com.instagram.android")
    try {
        activity.startActivity(likeIng)
    } catch (e: ActivityNotFoundException) {
        activity.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/uchqun_amrullaev/"))
        )
    }
}

fun openFacebook(activity: Activity) {
    val appFb = "fb://page/100071033274433"
    val urlFb = "https://www.facebook.com/profile.php?id=100071033274433&mibextid=ZbWKwL"
    try {
        if (isAppInstalled(activity, "com.facebook.orca") || isAppInstalled(
                activity,
                "com.facebook.katana"
            )
            || isAppInstalled(activity, "com.example.facebook") || isAppInstalled(
                activity,
                "com.facebook.android"
            )
        ) {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(appFb)))
        } else {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlFb)))
        }
    } catch (e: Exception) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlFb)))
        e.printStackTrace()
    }
}

fun openWebsite(activity: Activity) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://aksiyamix.uz/"))
    activity.startActivity(intent)
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    return try {
        context.packageManager.getApplicationInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}
