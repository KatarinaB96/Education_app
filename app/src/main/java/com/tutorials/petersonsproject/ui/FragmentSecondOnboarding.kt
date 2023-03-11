package com.tutorials.petersonsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.databinding.FragmentSecondSplashBinding


class FragmentSecondOnboarding : Fragment() {
    private lateinit var binding: FragmentSecondSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondSplashBinding.inflate(layoutInflater)
        return binding.root
    }

}