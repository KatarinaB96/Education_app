package com.tutorials.petersonsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentExamBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.router.Router
import javax.inject.Inject

private const val FIRST_PAGE_POSITION_EXAM = 0
private const val SECOND_PAGE_POSITION_EXAM = 1

class FragmentExam : BaseFragment() {
    companion object {
        private const val EXAM_ID = "exam_id"
        private const val EXAMTITLE = "title"


        fun createInstance(id: Long, title: String) = FragmentExam().apply {
            arguments = Bundle().apply {
                putString(EXAMTITLE, title)
                putLong(EXAM_ID, id)
            }
        }
    }

    private lateinit var binding: FragmentExamBinding

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @Inject
    lateinit var router: Router


    private val titleExam: String by lazy { arguments?.getString(EXAMTITLE) ?: "" }
    private val examId: Long by lazy { arguments?.getLong(EXAM_ID) ?: 0 }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val pager = binding.viewPagerExam

        pager.adapter = ExamAdapter(childFragmentManager, lifecycle, examId)
        pager.offscreenPageLimit = 2

        binding.backButton.setOnClickListener {
            router.routerPopBack()
        }
        binding.titleExam.text = titleExam



        TabLayoutMediator(binding.tablayout, pager) { tab, position ->
            when (position) {
                FIRST_PAGE_POSITION_EXAM -> tab.apply {
                    text = "Overview"
                }
                SECOND_PAGE_POSITION_EXAM -> tab.text = "Course Content"
            }
        }.attach()
    }
}