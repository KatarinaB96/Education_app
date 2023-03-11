package com.tutorials.petersonsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.databinding.FragmentThirdOnboardingBinding


class FragmentThirdOnboarding : Fragment() {
    private lateinit var binding: FragmentThirdOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdOnboardingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


}