package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.ui.screenmodels.CoursesScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.TopicsScreenModel

interface TopicViewModel {
    val topicContent: MutableLiveData<TopicsScreenModel>
    fun getDataAboutTopics()
//    fun getCookies()
//
//    val cookies: MutableLiveData<SessionCookie>
}