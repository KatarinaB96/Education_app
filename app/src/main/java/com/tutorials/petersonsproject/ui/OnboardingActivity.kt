package com.tutorials.petersonsproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.databinding.ActivitySplashBinding
import com.tutorials.petersonsproject.di.activity.ActivityComponent



//const val FIRST_PAGE_POSITION_ON_BOARDING = 0
//const val SECOND_PAGE_POSITION_ON_BOARDING = 1
const val THIRD_PAGE_POSITION = 2
const val FORTH_PAGE_POSITION = 3

class OnboardingActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pager = binding.viewPager
        pager.adapter = OnboardingAdapter(supportFragmentManager, lifecycle)

        slidersClickListener(pager)

        binding.skip.setOnClickListener {
            val intentHomeActivity = Intent(this, HomeActivity::class.java)
            startActivity(intentHomeActivity)
            finish()
        }

        val dotsIndicator = binding.dotsIndicator

        dotsIndicator.setViewPager2(pager)

    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    private fun slidersClickListener(pager: ViewPager2) {
        binding.skipArrow.setOnClickListener {
            when (pager.currentItem) {
                FIRST_PAGE_POSITION -> pager.currentItem = SECOND_PAGE_POSITION
                SECOND_PAGE_POSITION -> pager.currentItem = THIRD_PAGE_POSITION
                THIRD_PAGE_POSITION -> pager.currentItem = FORTH_PAGE_POSITION
                FORTH_PAGE_POSITION -> {
                    val intentHomeActivity = Intent(this, HomeActivity::class.java)
                    startActivity(intentHomeActivity)
                    finish()
                }
            }
        }
    }
}