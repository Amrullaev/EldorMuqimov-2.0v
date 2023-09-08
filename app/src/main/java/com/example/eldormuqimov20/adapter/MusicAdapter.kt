package com.example.eldormuqimov20.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.databinding.ItemMusicBinding
import com.example.eldormuqimov20.domain.MusicData
import com.google.common.reflect.Reflection.getPackageName
import kotlin.math.abs
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class MusicAdapter(
    private val mylist: List<MusicData>,
    private val listener: OnClickListener,
    context: Context
) :
    RecyclerView.Adapter<MusicAdapter.VH>() {


    inner class VH(var itemMusicBinding: ItemMusicBinding) :
        RecyclerView.ViewHolder(itemMusicBinding.root) {
        @SuppressLint("DiscouragedApi")
        fun onBind(musicData: MusicData, position: Int) {
            itemMusicBinding.artistTxt.text = "Eldor Muqimov"
            itemMusicBinding.songNameTxt.text = musicData.audioName
            itemMusicBinding.songImage.setImageResource(musicData.audioImage)
            itemMusicBinding.songTime.text = musicData.durationMin
            itemMusicBinding.songtimesec.text = musicData.durationSec
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