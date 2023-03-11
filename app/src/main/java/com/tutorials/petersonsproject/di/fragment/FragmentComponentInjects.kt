package com.tutorials.petersonsproject.di.fragment

import com.tutorials.petersonsproject.ui.*


interface FragmentComponentInjects {
    fun inject(fragment: FragmentFirstOnboarding)
    fun inject(fragment: FragmentSecondOnboarding)
    fun inject(fragment: FragmentThirdOnboarding)
    fun inject(fragment: FragmentForthOnboarding)

    fun inject(fragmentHome: FragmentHome)
    fun inject(fragmentProfile: FragmentProfile)

    fun inject(fragmentExam: FragmentExam)
    fun inject(fragmentCourseContents: FragmentCourseContents)

    fun inject(fragmentOverview: FragmentOverview)
    fun inject(fragmentContent: FragmentContent)

    fun inject(lessonFragment: LessonFragment)
    fun inject(fragment: FolderFragment)


    fun inject(fragmentHomeContainerBinding: HomeContainerFragment)

    fun inject(fragment: ProfileContainerFragment)

}