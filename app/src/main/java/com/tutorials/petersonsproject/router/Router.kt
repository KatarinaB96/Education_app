package com.tutorials.petersonsproject.router

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.ui.screenmodels.TopicScreenModel


interface Router {
    fun routerPopBack()


    fun showHomeFragment()
    fun showProfileFragment()
    fun showExamFragment(id: Long, title: String)

    fun showContent()
    fun showLesson(url: String)

    fun showQuiz()
    fun showFolder(contents: List<TopicScreenModel>)

}