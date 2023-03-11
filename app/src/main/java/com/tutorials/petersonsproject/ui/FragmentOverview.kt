package com.tutorials.petersonsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentOverviewBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.ui.adapters.FaqAdapter
import com.tutorials.petersonsproject.ui.adapters.OverviewAdapter
import com.tutorials.petersonsproject.ui.viewmodels.CourseViewModel
import com.tutorials.petersonsproject.ui.viewmodels.FaqViewModel
import com.tutorials.petersonsproject.ui.viewmodels.OverviewViewModel
import javax.inject.Inject


class FragmentOverview() : BaseFragment() {
    private lateinit var binding: FragmentOverviewBinding

    private var overviewAdapter: OverviewAdapter? = null
    private var faqAdapter: FaqAdapter? = null

    @Inject
    lateinit var overviewViewModel: OverviewViewModel

    @Inject
    lateinit var faqViewModel: FaqViewModel

    @Inject
    lateinit var courseViewModel: CourseViewModel


    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        overviewViewModel.getDataAboutOverviews()
        faqViewModel.getDataAboutFaqs()

        initAdapterCourseCategory()
        observeData()
    }

    private fun initAdapterCourseCategory() {

        binding.overViewRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            overviewAdapter = OverviewAdapter()
            adapter = overviewAdapter
        }

        binding.faqRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            faqAdapter = FaqAdapter()
            adapter = faqAdapter
        }

    }

    private fun observeData() {
        overviewViewModel.overview.observe(requireActivity()) {
            overviewAdapter?.submitList(it.overviews)
        }

        faqViewModel.faq.observe(requireActivity()) {
            faqAdapter?.submitList(it.faqs)
        }
    }


}