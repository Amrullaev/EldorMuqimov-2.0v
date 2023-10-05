package com.example.eldormuqimov20.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.databinding.FragmentMusicBinding
import com.example.eldormuqimov20.domain.MusicData
import com.example.eldormuqimov20.responseUtils.Constanta
import com.example.eldormuqimov20.service.MyService

class MusicFragment : Fragment(), OnPreparedListener {
    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!

    private var handler = Handler()
    private lateinit var songs: ArrayList<MusicData>
    private var mediaPlayer: MediaPlayer = MediaPlayer()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicBinding.inflate(layoutInflater)

        val intent = Intent(requireContext(), MyService::class.java)

        songs = ArrayList()
        var music = requireArguments().getInt("audio")
        val musicName = arguments?.get("music") as MusicData

        songs = Constanta.musicList
        Log.d("TAG", "onCreateView: $songs")

        binding.songName.text = musicName.audioName
        binding.songImage.setImageResource(musicName.audioImage)

        mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }



        binding.seekbar.progress = 0
        binding.seekbar.max = mediaPlayer.duration

        binding.playBtn.setOnClickListener {
            if (!mediaPlayer.isPlaying) {


                mediaPlayer.start()
                requireActivity().startService(intent)
                binding.playBtn.setImageResource(R.drawable.pause_142)
            } else {
                mediaPlayer.pause()
                binding.playBtn.setImageResource(R.drawable.play_btn)
            }
        }


        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })




        mediaPlayer.setOnCompletionListener {
            binding.seekbar.progress = 0

            if (music < songs.size - 1) {
                music += 1
            } else {
                music = 0
            }
            binding.songImage.setImageResource(songs[music].audioImage)
            binding.songName.text = songs[music].audioName

            try {
                requireActivity().stopService(intent)
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)
                requireActivity().startService(intent)
                mediaPlayer.start()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        binding.nextBtn.setOnClickListener {
            if (music < songs.size - 1) {
                music += 1
            } else {
                music = 0
            }
            binding.songImage.setImageResource(songs[music].audioImage)
            binding.songName.text = songs[music].audioName
            try {
                requireActivity().stopService(intent)
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)
                requireActivity().startService(intent)
                mediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.previousBtn.setOnClickListener {
            if (music > 0) {
                music -= 1

            } else {
                music = songs.size - 1
            }
            binding.songImage.setImageResource(songs[music].audioImage)
            binding.songName.text = songs[music].audioName
            try {
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)
                mediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)

        return binding.root
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()

    }

    private val runnable = object : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {

            val currentPosition = mediaPlayer.currentPosition
            Log.d("position", "run: $currentPosition")
            binding.seekbar.progress = currentPosition

            if ((currentPosition % 60000) / 1000 > 9 && currentPosition / 60000 > 9) {
                binding.startTimeTxt.text =
                    "${currentPosition / 60000}:${(currentPosition % 60000) / 1000}"
            }
            if (currentPosition / 60000 < 10) {
                binding.startTimeTxt.text =
                    "0${currentPosition / 60000}:${(currentPosition % 60000) / 1000}"
            }
            if ((currentPosition % 60000) / 1000 < 10) {
                binding.startTimeTxt.text =
                    "${currentPosition / 60000}:0${(currentPosition % 60000) / 1000}"
            }
            if ((currentPosition % 60000) / 1000 < 10 && currentPosition / 60000 < 10) {
                binding.startTimeTxt.text =
                    "0${currentPosition / 60000}:0${(currentPosition % 60000) / 1000}"
            }
            handler.postDelayed(this, 1000)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.isLooping = false
        mediaPlayer.pause()

        mediaPlayer.release()
        handler.removeCallbacks(runnable)
        _binding = null
    }
}