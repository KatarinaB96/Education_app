package com.tutorials.petersonsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.databinding.FragmentIntro2Binding


class FragmentIntro2 : Fragment() {

    private lateinit var binding: FragmentIntro2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro2Binding.inflate(layoutInflater)
        return binding.root
    }

}