package com.example.eldormuqimov20.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.eldormuqimov20.domain.MusicData

class MyService : Service() {
    lateinit var musicdata: MusicData
    private var mediaPlayer: MediaPlayer? = null



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }

        mediaPlayer = MediaPlayer.create(this, musicdata.id)
        mediaPlayer?.start()
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