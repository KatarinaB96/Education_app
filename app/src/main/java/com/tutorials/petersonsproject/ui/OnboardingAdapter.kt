package com.tutorials.petersonsproject.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tutorials.petersonsproject.ui.FragmentIntro1
import com.tutorials.petersonsproject.ui.FragmentIntro2

class OnboardingAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentFirstOnboarding()
            1 -> return FragmentSecondOnboarding()
            2 -> return FragmentThirdOnboarding()
            3 -> return FragmentForthOnboarding()
            else -> return FragmentFirstOnboarding()
        }
    }

}