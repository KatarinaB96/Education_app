package com.tutorials.petersonsproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentProfileBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.router.Router
import javax.inject.Inject

class FragmentProfile : BaseFragment() {

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var courseDataSource: CourseDataSource

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseDataSource.user.observe(requireActivity()) {
            val nameOfTheUser = it.userFirstName + " " + it.userLastName
            binding.userName.text = nameOfTheUser
        }

        binding.settings.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}