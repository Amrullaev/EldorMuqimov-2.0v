package com.example.eldormuqimov20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eldormuqimov20.R
import com.example.eldormuqimov20.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater)


        binding.btnLanguage.setOnClickListener {
            findNavController().navigate(R.id.languageFragment)
        }


        binding.btnTheme.setOnClickListener {
            findNavController().navigate(R.id.themeFragment)
        }


        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }



        return binding.root
    }


}