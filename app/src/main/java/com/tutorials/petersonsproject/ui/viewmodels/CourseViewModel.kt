package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.ui.screenmodels.CoursesScreenModel

interface CourseViewModel {
    val course: MutableLiveData<CoursesScreenModel>
    fun getDataAboutCourse()
    fun setOverviewData(courseCategoryID: String, examID: Long)

    //    fun setOverviews(title: String, titleExam: String)
    fun authenticate()

    fun dataAboutUser()

}