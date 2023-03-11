package com.tutorials.petersonsproject.domain.publishSubject

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.domain.user.User
import io.reactivex.subjects.BehaviorSubject

interface CourseDataSource {
    fun setOverviews(overview: List<Overview>)
    fun observeOverviews(): BehaviorSubject<List<Overview>>

    fun setFaq(faq: List<Faq>)
    fun observeFaqs(): BehaviorSubject<List<Faq>>

    fun setUserName(user: User)
    fun observeUser(): BehaviorSubject<User>

    val user: MutableLiveData<User>
    fun getDataUser()
}