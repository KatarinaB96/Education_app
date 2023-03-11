package com.tutorials.petersonsproject.di.activity

import com.tutorials.petersonsproject.ui.HomeActivity
import com.tutorials.petersonsproject.ui.MainActivity
import com.tutorials.petersonsproject.ui.OnboardingActivity


interface ActivityComponentInjects {

    fun inject(activity: MainActivity)
    fun inject(activity: OnboardingActivity)
    fun inject(activity: HomeActivity)

}