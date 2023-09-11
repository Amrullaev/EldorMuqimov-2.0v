package com.example.eldormuqimov20.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.eldormuqimov20.R

class MyService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val rawId = intent?.extras?.getInt("resId", R.raw.dema)

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        rawId?.let {
            mediaPlayer = MediaPlayer.create(this, rawId)
            mediaPlayer?.start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

}
