package com.tutorials.petersonsproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tutorials.petersonsproject.ui.FragmentIntro1
import com.tutorials.petersonsproject.ui.FragmentIntro2

class IntroAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {

        private const val NUMBER_OF_FRAGMENTS = 2
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1
    }

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FIRST_POSITION -> FragmentIntro1()
            SECOND_POSITION -> FragmentIntro2()
            else -> FragmentIntro1()
        }
    }

}