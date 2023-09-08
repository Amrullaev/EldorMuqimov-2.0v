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




