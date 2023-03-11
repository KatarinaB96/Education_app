package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.ui.screenmodels.CourseScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.CoursesScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.OverViewScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.OverviewsScreenModel

interface OverviewViewModel {
    val overview: MutableLiveData<OverviewsScreenModel>
    fun getDataAboutOverviews()

}