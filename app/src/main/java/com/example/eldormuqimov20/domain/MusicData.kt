package com.example.eldormuqimov20.domain

import java.io.Serializable


data class MusicData(
    var id: Int,
    val audioName: String = "",
    val audioMP3: String,
    val audioImage: Int,
    var currentIndex: Int
) : Serializable
