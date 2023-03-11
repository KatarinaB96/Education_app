package com.tutorials.petersonsproject.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tutorials.petersonsproject.ui.*

class BottomNavAdapter(activity: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(activity,lifecycle) {

    companion object {

        private const val NUMBER_OF_FRAGMENTS = 2
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1
    }

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FIRST_POSITION -> HomeContainerFragment()
            SECOND_POSITION -> ProfileContainerFragment()
            else -> FragmentIntro1()
        }
    }

}