package com.example.eldormuqimov20.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.Resources
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.databinding.ItemMusicBinding
import com.example.eldormuqimov20.domain.MusicData
import java.util.Locale
import java.util.concurrent.TimeUnit

class MusicAdapter(
    private val mylist: List<MusicData>,
    private val listener: OnClickListener,
    context: Context
) :
    RecyclerView.Adapter<MusicAdapter.VH>() {

    val resources = context.resources
    val packageName = context.packageName


    inner class VH(var itemMusicBinding: ItemMusicBinding) :
        RecyclerView.ViewHolder(itemMusicBinding.root) {
        @SuppressLint("DiscouragedApi")
        fun onBind(musicData: MusicData, position: Int) {
            itemMusicBinding.artistTxt.text = "Eldor Muqimov"
            itemMusicBinding.songNameTxt.text = musicData.audioName
            itemMusicBinding.songImage.setImageResource(musicData.audioImage)

            val rawIds = mutableListOf<Int>()
            for (i in 1..3) { // Assuming there are 10 songs in the raw folder
                val resourceId = resources.getIdentifier("song$i", "raw", packageName)
                if (resourceId != 0) {
                    rawIds.add(resourceId)
                }
            }

            val mediaPlayer = MediaPlayer()
            for (rawId in rawIds) {
                mediaPlayer.reset()
                val fileDescriptor = resources.openRawResourceFd(rawId)
                mediaPlayer.setDataSource(
                    fileDescriptor.fileDescriptor,
                    fileDescriptor.startOffset,
                    fileDescriptor.length
                )
                mediaPlayer.prepare()

                val duration = mediaPlayer.duration // Length of the song in milliseconds

                // Convert duration to minutes and seconds if needed
                val minutes = TimeUnit.MILLISECONDS.toMinutes(duration.toLong())
                val seconds = TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) % 60
                itemMusicBinding.songTime.text = "$minutes:$seconds"
                Log.d("Song Length", "Song $rawId: $minutes minutes, $seconds seconds")

                mediaPlayer.release()
            }

            itemMusicBinding.root.setOnClickListener {
                listener.onClick(musicData, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(mylist[position], position)
    }

    interface OnClickListener {
        fun onClick(musicData: MusicData, position: Int)
    }

}