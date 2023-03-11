package com.tutorials.petersonsproject.ui

import android.os.Bundle
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.databinding.ActivityHomeBinding
import com.tutorials.petersonsproject.di.activity.ActivityComponent
import com.tutorials.petersonsproject.router.Router
import com.tutorials.petersonsproject.ui.adapters.BottomNavAdapter
import javax.inject.Inject


class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding


    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//

        val pager = binding.pager

        pager.isUserInputEnabled = false

        pager.adapter = BottomNavAdapter(supportFragmentManager, lifecycle)
        pager.offscreenPageLimit = 2


        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeNavigation -> pager.currentItem = 0
                R.id.profileNavigation -> pager.currentItem = 1
            }
            true
        }

    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

}