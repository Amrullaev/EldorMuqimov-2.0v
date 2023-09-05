package com.example.eldormuqimov20.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eldormuqimov20.MainActivity
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.databinding.FragmentAboutBinding
import com.example.eldormuqimov20.databinding.FragmentSettingsBinding
import com.example.eldormuqimov20.responseUtils.openInstagram
import com.example.eldormuqimov20.responseUtils.openTelegram

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(layoutInflater)
        binding.telegramBtn.setOnClickListener {
            openTelegram(requireActivity())
        }

        binding.instagramBtn.setOnClickListener {
            openInstagram(requireActivity())
        }

        binding.youtubeBtn.setOnClickListener {
            open("https://www.youtube.com/@eldormuqimov9341")
        }

        binding.telNumber.setOnClickListener {
            val phone = "+998936968890"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

        return binding.root
    }

    private fun open(uri: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }
}