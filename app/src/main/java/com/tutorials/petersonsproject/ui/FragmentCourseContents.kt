package com.tutorials.petersonsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentCourseContentsBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.router.Router
import com.tutorials.petersonsproject.ui.adapters.CourseContentAdapter
import com.tutorials.petersonsproject.ui.screenmodels.ModuleContentScreenModel
import com.tutorials.petersonsproject.ui.viewmodels.ModuleContentViewModel
import javax.inject.Inject


class FragmentCourseContents(private val examID: Long) : BaseFragment() {
    private lateinit var binding: FragmentCourseContentsBinding
    private var contentAdapter: CourseContentAdapter? = null


    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var moduleContentViewModel: ModuleContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseContentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loader.visibility = View.VISIBLE

        if (moduleContentViewModel.modules.value == null) {
            moduleContentViewModel.getDataAboutHierarchyContent(examID)
        }


        initAdapter()
        observeData()
    }

    private fun initAdapter() {
        contentAdapter = CourseContentAdapter { onClick(it) }

        binding.courseContentRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.courseContentRecyclerView.adapter = contentAdapter
    }

    private fun onClick(moduleContentScreenModel: ModuleContentScreenModel) {
        moduleContentViewModel.setTopicData(moduleContentScreenModel.id)
        router.showContent()
    }

    private fun observeData() {
        moduleContentViewModel.modules.observe(requireActivity()) {
            contentAdapter?.submitList(it.moduleContent)
            binding.loader.visibility = View.GONE
        }
    }

}