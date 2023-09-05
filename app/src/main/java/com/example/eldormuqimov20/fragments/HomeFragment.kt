package com.example.eldormuqimov20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.adapter.MusicAdapter
import com.example.eldormuqimov20.databinding.FragmentHomeBinding
import com.example.eldormuqimov20.domain.MusicData
import com.example.eldormuqimov20.responseUtils.Constanta


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)



        binding.settingsBtn.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        val musicAdapter = MusicAdapter(audioLoadList(), object : MusicAdapter.OnClickListener {
            override fun onClick(musicData: MusicData, position: Int) {
                val bundle = Bundle()
                bundle.putInt("audio", position)
                bundle.putSerializable("music", musicData)
                findNavController().navigate(R.id.musicFragment, bundle)
            }

        })
        binding.recycler.adapter = musicAdapter
        Constanta.musicList = audioLoadList()
        return binding.root
    }

    private fun audioLoadList(): ArrayList<MusicData> {
        val list = ArrayList<MusicData>()

        list.add(
            MusicData(
                R.raw.yiqilganni_tepma_dostim,
                "Yiqilganni tepma do'stim",
                "yiqilganni_tepma_dostim.mp3",
                R.drawable.eldor10,
                1
            )
        )

        list.add(
            MusicData(
                R.raw.qalandarim,
                "Qalandarim",
                "yiqilganni_tepma_dostim.mp3",
                R.drawable.eldor7,
                2
            )
        )
        list.add(
            MusicData(
                R.raw.xayifdur,
                "Xayifdur",
                "xayifdur.mp3",
                R.drawable.eldor8,
                3
            )
        )



        return list
    }

}