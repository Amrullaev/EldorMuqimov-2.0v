package com.example.eldormuqimov20.fragments


import android.content.Intent
import android.media.MediaPlayer
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
import com.example.eldormuqimov20.service.MyService


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)



        binding.settingsBtn.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        val musicAdapter = context?.let {
            MusicAdapter(audioLoadList(), object : MusicAdapter.OnClickListener {
                override fun onClick(musicData: MusicData, position: Int) {
                    val bundle = Bundle()
                    bundle.putInt("audio", position)
                    bundle.putSerializable("music", musicData)
                    findNavController().navigate(R.id.musicFragment, bundle)

                    requireActivity().startService(Intent(requireContext(), MyService::class.java))
//                    if (mediaPlayer?.isPlaying == true) {
//                        mediaPlayer?.stop()
//                    }
//
//                    mediaPlayer = MediaPlayer.create(requireContext(), musicData.id)
//                    mediaPlayer?.start()
                }


            }, it)
        }
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
                14,
                "04",
                "03"
            )
        )

        list.add(
            MusicData(
                R.raw.qalandarim,
                "Qalandarim",
                "yiqilganni_tepma_dostim.mp3",
                R.drawable.eldor7,
                18,
                "03",
                "50"
            )
        )
        list.add(
            MusicData(
                R.raw.xayifdur,
                "Xayifdur",
                "xayifdur.mp3",
                R.drawable.eldor8,
                7,
                "04",
                "42"
            )
        )
        list.add(
            MusicData(
                R.raw.omonat,
                "Omonat",
                "omonat.mp3",
                R.drawable.eldor11,
                17,
                "06",
                "06"
            )
        )

        list.add(
            MusicData(
                R.raw.chapandozi_va_savti_suvora,
                "Chapandozi va Savti suvora",
                "chapandozi_va_savti_suvora.mp3",
                R.drawable.eldor8,
                13,
                "10",
                "17"
            )
        )

        list.add(
            MusicData(
                R.raw.dema,
                "Dema",
                "dema.mp3",
                R.drawable.eldor10,
                6,
                "05",
                "21"
            )
        )

        list.add(
            MusicData(
                R.raw.gamzasin,
                "G'amzasin",
                "gamzasin.mp3",
                R.drawable.eldor11,
                15,
                "05",
                "34"
            )
        )

        list.add(
            MusicData(
                R.raw.kormaganni_korgani_qursin,
                "Ko'rmaganni ko'rgani qursin",
                "kormaganni_korgani_qursin.mp3",
                R.drawable.eldor9,
                8,
                "05",
                "19"
            )
        )

        list.add(
            MusicData(
                R.raw.maxsus,
                "Maxsus",
                "maxsus.mp3",
                R.drawable.eldor10,
                9,
                "06",
                "23"
            )
        )

        list.add(
            MusicData(
                R.raw.na_tilar_mandan,
                "Na tilar mandan",
                "na_tilar_mandan.mp3",
                R.drawable.eldor7,
                10,
                "05",
                "42"
            )
        )

        list.add(
            MusicData(
                R.raw.nafoyda,
                "Na foyda",
                "nafoyda.mp3",
                R.drawable.eldor11,
                11,
                "06",
                "39"
            )
        )

        list.add(
            MusicData(
                R.raw.otajonim_asra_xudoyim,
                "Otajonim asra xudoyim",
                "otajonim_asra_xudoyim.mp3",
                R.drawable.eldor9,
                2,
                "06",
                "56"
            )
        )

        list.add(
            MusicData(
                R.raw.savob_topmassan,
                "Savob topmassan",
                "savob_topmassan.mp3",
                R.drawable.eldor8,
                1,
                "05",
                "38"
            )
        )

        list.add(
            MusicData(
                R.raw.soginaman,
                "Sog'inaman",
                "soginaman.mp3",
                R.drawable.eldor10,
                12,
                "05",
                "16"
            )
        )

        list.add(
            MusicData(
                R.raw.togman_dema,
                "Tog'man dema",
                "togman_dema.mp3",
                R.drawable.eldor7,
                5,
                "05",
                "18"
            )
        )

        list.add(
            MusicData(
                R.raw.unutmanglar,
                "Unutmanglar",
                "unutmanglar.mp3",
                R.drawable.eldor9,
                3,
                "05",
                "58"
            )
        )

        list.add(
            MusicData(
                R.raw.yaxshidur,
                "Yaxshidur",
                "yaxshidur.mp3",
                R.drawable.eldor10,
                17,
                "05",
                "23"
            )
        )

        list.sortBy {
            it.currentIndex
        }



        return list
    }


}