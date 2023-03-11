package com.tutorials.petersonsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.R
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.router.Router
import javax.inject.Inject


class ProfileContainerFragment : BaseFragment() {
    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        router.showProfileFragment()
        return inflater.inflate(R.layout.fragment_profile_container, container, false)
    }

}