package com.tutorials.petersonsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.databinding.FragmentForthOnboardingBinding

class FragmentForthOnboarding : Fragment() {
    private lateinit var binding: FragmentForthOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForthOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

}