package com.example.eldormuqimov20.fragments

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
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.databinding.FragmentMusicBinding
import com.example.eldormuqimov20.domain.MusicData
import com.example.eldormuqimov20.responseUtils.Constanta

class MusicFragment : Fragment(), OnPreparedListener {
    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!
    private lateinit var runnable: Runnable
    private var handler = Handler()
    private lateinit var songs: ArrayList<MusicData>
    private var mediaPlayer: MediaPlayer = MediaPlayer()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicBinding.inflate(layoutInflater)

        songs = ArrayList()
        var music = requireArguments().getInt("audio")
        val musicName = arguments?.get("music") as MusicData

        songs = Constanta.musicList
        Log.d("TAG", "onCreateView: $songs")

        binding.songName.text = musicName.audioName
        binding.songImage.setImageResource(musicName.audioImage)

        mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)




        binding.seekbar.progress = 0
        binding.seekbar.max = mediaPlayer.duration

        binding.playBtn.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
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


        runnable = Runnable {
            binding.seekbar.progress = mediaPlayer.currentPosition

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)


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
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)
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
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(requireContext(), songs[music].id)
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


        return binding.root
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }




    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.isLooping = false
        mediaPlayer.stop()
        mediaPlayer.release()
        handler.removeCallbacks(runnable)
        _binding = null
    }
}