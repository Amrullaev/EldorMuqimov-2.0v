package com.example.eldormuqimov20.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldormuqimov20.databinding.ItemMusicBinding
import com.example.eldormuqimov20.domain.MusicData
import java.util.Locale

class MusicAdapter(private val mylist: List<MusicData>, private val listener: OnClickListener) :
    RecyclerView.Adapter<MusicAdapter.VH>() {

    inner class VH(var itemMusicBinding: ItemMusicBinding) :
        RecyclerView.ViewHolder(itemMusicBinding.root) {
        fun onBind(musicData: MusicData,position: Int) {
            itemMusicBinding.artistTxt.text = "Eldor Muqimov"
            itemMusicBinding.songNameTxt.text = musicData.audioName
            itemMusicBinding.songImage.setImageResource(musicData.audioImage)
//            val duration = musicData.duration.toLong()
//            val min = duration / 1000 / 60
//            val second = duration / 1000 - min * 60
//            itemMusicBinding.songTime.text = "$min:$second"
            itemMusicBinding.root.setOnClickListener {
                listener.onClick(musicData,position)
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
        holder.onBind(mylist[position],position)
    }

    interface OnClickListener {
        fun onClick(musicData: MusicData,position: Int)
    }

//    fun format(millis: Long): String {
//        val allSeconds = millis / 1000
//        val allMinutes: Int
//        val seconds: Byte
//        val minutes: Byte
//        val hours: Byte
//        if (allSeconds >= 60) {
//            allMinutes = (allSeconds / 60).toInt()
//            seconds = (allSeconds % 60).toByte()
//            return if (allMinutes >= 60) {
//                hours = (allMinutes / 60).toByte()
//                minutes = (allMinutes % 60).toByte()
//                String.format(
//                    Locale.US,
//                    "%d:%d:" + formatSeconds(seconds),
//                    hours,
//                    minutes,
//                    seconds
//                )
//            } else
//                String.format(Locale.US, "%d:" + formatSeconds(seconds), allMinutes, seconds)
//        } else
//            return String.format(Locale.US, "0:" + formatSeconds(allSeconds.toByte()), allSeconds)
//    }
//
//    fun formatSeconds(seconds: Byte): String {
//        return if (seconds < 10) "0%d" else "%d"
//    }

}