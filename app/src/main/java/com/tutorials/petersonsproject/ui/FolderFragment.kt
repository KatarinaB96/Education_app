package com.tutorials.petersonsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentFolderBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.router.Router
import com.tutorials.petersonsproject.ui.adapters.TopicContentAdapter
import com.tutorials.petersonsproject.ui.screenmodels.TopicScreenModel
import com.tutorials.petersonsproject.ui.viewmodels.TopicViewModel
import javax.inject.Inject


class FolderFragment(private val contents: List<TopicScreenModel>) : BaseFragment() {
    private lateinit var binding: FragmentFolderBinding
    private var topicAdapter: TopicContentAdapter? = null

    @Inject
    lateinit var topicViewModel: TopicViewModel

    @Inject
    lateinit var router: Router

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFolderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topicViewModel.getDataAboutTopics()
        initAdapterCourseCategory()
        observeData()
    }


    private fun onClick(topicsScreenModel: TopicScreenModel) {
        if (topicsScreenModel.type == 1) {
            if (topicsScreenModel.topicType == 1) {

                router.showLesson(topicsScreenModel.url)


            }
            if (topicsScreenModel.topicType == 3) {
                router.showQuiz()
            }
        }
        if (topicsScreenModel.type == 0) {
            router.showFolder(topicsScreenModel.contents)
        }
    }

    private fun initAdapterCourseCategory() {

        binding.folderRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            topicAdapter = TopicContentAdapter { onClick(it) }

            adapter = topicAdapter

            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        }
    }

    private fun observeData() {
        topicAdapter?.submitList(contents)

//            binding.loader.visibility = View.GONE
//        }
    }

}