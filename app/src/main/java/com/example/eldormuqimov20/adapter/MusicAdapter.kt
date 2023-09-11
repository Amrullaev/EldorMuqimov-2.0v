package com.example.eldormuqimov20.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldormuqimov20.databinding.ItemMusicBinding
import com.example.eldormuqimov20.domain.MusicData


class MusicAdapter(
    private val mylist: List<MusicData>,
    private val onClick: (MusicData, Int) -> Unit
) :
    RecyclerView.Adapter<MusicAdapter.VH>() {


    inner class VH(var itemMusicBinding: ItemMusicBinding) :
        RecyclerView.ViewHolder(itemMusicBinding.root) {
        fun onBind(musicData: MusicData, position: Int) {
            itemMusicBinding.artistTxt.text = "Eldor Muqimov"
            itemMusicBinding.songNameTxt.text = musicData.audioName
            itemMusicBinding.songImage.setImageResource(musicData.audioImage)
            itemMusicBinding.songTime.text = musicData.durationMin
            itemMusicBinding.songtimesec.text = musicData.durationSec
            itemMusicBinding.root.setOnClickListener {
                onClick(musicData, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = mylist.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(mylist[position], position)
    }

}
