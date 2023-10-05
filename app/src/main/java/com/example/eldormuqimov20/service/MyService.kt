package com.example.eldormuqimov20.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.IBinder
import android.util.Log
import com.example.eldormuqimov20.R

class MyService : Service(), OnPreparedListener {
    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val rawId = intent?.extras?.getInt("resId", R.raw.dema)

        if (mediaPlayer?.isPlaying == true) {
            stopMusic()
        }
        rawId?.let {
            mediaPlayer = MediaPlayer.create(this, rawId)
            playMusic()
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

    fun pauseMusic() {
        mediaPlayer?.pause()
        Log.d(javaClass.simpleName, "pauseMusic()")
    }

    fun playMusic() {
        mediaPlayer?.start()

        Log.d(javaClass.simpleName, "start()")
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        Log.d(javaClass.simpleName, "stop()")
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

}
