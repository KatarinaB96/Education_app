package com.tutorials.petersonsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentFirstSplashBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent


class FragmentFirstOnboarding : BaseFragment() {
    private lateinit var binding: FragmentFirstSplashBinding

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstSplashBinding.inflate(layoutInflater)
        return binding.root
    }

}