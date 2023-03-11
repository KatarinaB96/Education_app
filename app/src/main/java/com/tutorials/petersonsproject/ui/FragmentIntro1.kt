package com.tutorials.petersonsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.databinding.FragmentIntro1Binding


class FragmentIntro1 : Fragment() {
    private lateinit var binding: FragmentIntro1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIntro1Binding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

}