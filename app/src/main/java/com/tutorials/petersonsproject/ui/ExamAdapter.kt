package com.tutorials.petersonsproject.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ExamAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val examId: Long
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    companion object {

        private const val NUMBER_OF_FRAGMENTS = 2
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1
    }

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FIRST_POSITION -> FragmentOverview()
            SECOND_POSITION -> FragmentCourseContents(examId)
            else -> FragmentOverview()
        }
    }

}