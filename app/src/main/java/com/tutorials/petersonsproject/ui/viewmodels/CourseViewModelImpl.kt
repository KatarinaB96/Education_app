package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.base.BaseViewModel
import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.CourseRepository
import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.ui.screenmodels.CourseScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.CoursesScreenModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CourseViewModelImpl(
    private val repository: CourseRepository,
    private val courseDataSourceImpl: CourseDataSource
) : CourseViewModel,
    BaseViewModel() {

    override val course: MutableLiveData<CoursesScreenModel> = MutableLiveData()


    val list: MutableList<CourseCategory> = mutableListOf()

    override fun getDataAboutCourse() {
        addDisposable(
            repository.getCourseCategory()
                .doOnNext { list.addAll(it) }
                .map {
                    CoursesScreenModel(it.map {
                        CourseScreenModel(it.id, it.title, it.exam)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ onGetCourseSuccess(it) }, { it.printStackTrace() })
        )
    }

    override fun dataAboutUser() {
        addDisposable(
            repository.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({courseDataSourceImpl.setUserName(it) }, { it.printStackTrace() })
        )
    }



    override fun authenticate() {
        addDisposable(
            repository.authenticate()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ }, { it.printStackTrace() })
        )
    }

    override fun setOverviewData(courseCategoryID: String, examID: Long) {
        val overview = list.find { it.id == courseCategoryID }
            ?.exam?.find { it.id == examID }
            ?.overview?.map {
                Overview(
                    it.image,
                    it.description
                )
            } ?: emptyList()

        courseDataSourceImpl.setOverviews(overview)

        val faq = list.find { it.id == courseCategoryID }
            ?.exam?.find { it.id == examID }
            ?.faq?.map {
                Faq(
                    it.question,
                    it.answer
                )
            } ?: emptyList()

        courseDataSourceImpl.setFaq(faq)
    }


    private fun onGetCourseSuccess(courseScreenModel: CoursesScreenModel) {
        course.postValue(courseScreenModel)
    }


//    override fun setOverviews(titleExam: String) {
//        listOfCourseCategory.map {
//            it.exam.filter { it.title == titleExam }.map {
//                examPublishSubjectImpl.setOverviews(it.overview.map {
//                    Overview(
//                        it.image,
//                        it.description
//                    )
//                })
//            }
//        }
//    }

}