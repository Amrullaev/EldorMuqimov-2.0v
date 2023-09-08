package com.example.eldormuqimov20.domain

import java.io.Serializable
import java.time.Duration


data class MusicData(
    var id: Int,
    val audioName: String = "",
    val audioMP3: String,
    val audioImage: Int,
    var currentIndex: Int,
    var durationMin:String,
    var durationSec:String
) : Serializable
