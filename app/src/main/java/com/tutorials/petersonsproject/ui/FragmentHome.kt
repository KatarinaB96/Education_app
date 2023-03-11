package com.tutorials.petersonsproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.textChanges
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.databinding.FragmentHomeBinding
import com.tutorials.petersonsproject.di.fragment.FragmentComponent
import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.Exam
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.router.Router
import com.tutorials.petersonsproject.ui.adapters.CourseCategoryAdapter
import com.tutorials.petersonsproject.ui.screenmodels.CourseScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.CoursesScreenModel
import com.tutorials.petersonsproject.ui.viewmodels.CourseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class FragmentHome : BaseFragment() {
    private var courseCategoryAdapter: CourseCategoryAdapter? = null
    private lateinit var binding: FragmentHomeBinding

    private var listOfCourses: MutableList<CourseScreenModel> = mutableListOf()

    val disposable = CompositeDisposable()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var courseViewModel: CourseViewModel

    @Inject
    lateinit var courseDataSource: CourseDataSource

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        courseViewModel.authenticate()
        binding = FragmentHomeBinding.inflate(layoutInflater)

        if (courseViewModel.course.value == null || listOfCourses.isNullOrEmpty()) {
            courseViewModel.getDataAboutCourse()
        }

        courseViewModel.dataAboutUser()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loader.visibility = View.VISIBLE
        courseDataSource.getDataUser()

        courseDataSource.user.observe(requireActivity()) {
            val first = it.userFirstName
            binding.nameTextView.text = "Hi, " + first
        }


        initAdapterCourseCategory()
        observeData()


        binding.searchBar.searchEditText
        val search = binding.searchBar.searchEditText.textChanges()
            .skipInitialValue()
//            .debounce(500, TimeUnit.MILLISECONDS)
            .map { searchedText ->
                // Perform API operation
                listOfCourses.filter {
                    it.title.lowercase(Locale.US)
                        .contains(searchedText.toString().lowercase(Locale.US).trim())
                }

            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({

                searchResults(it)
            }, {
            })

        disposable.add(search)


    }

    private fun searchResults(list: List<CourseScreenModel>) {
        if (list.isEmpty()) {
            binding.noFoundResults.visibility = View.VISIBLE
            binding.noFoundResults.text =
                "No result found for ${binding.searchBar.searchEditText.text}"
        } else {
            binding.noFoundResults.visibility = View.GONE
        }

        courseCategoryAdapter?.submitList(list)
    }


    private fun onItemClicked(exam: Exam) {
        courseViewModel.setOverviewData(exam.categoryId, exam.id)
        router.showExamFragment(exam.id, exam.title)
    }


    private fun initAdapterCourseCategory() {

        binding.topics.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            courseCategoryAdapter = CourseCategoryAdapter { (onItemClicked(it)) }

            adapter = courseCategoryAdapter

            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        }

    }

    private fun observeData() {
        courseViewModel.course.observe(requireActivity()) {
            listOfCourses = mutableListOf()
            listOfCourses.addAll(it.courseCategories)


            courseCategoryAdapter?.submitList(listOfCourses)
            binding.loader.visibility = View.GONE

        }
    }


}