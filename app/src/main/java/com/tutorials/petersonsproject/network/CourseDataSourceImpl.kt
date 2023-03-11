package com.tutorials.petersonsproject.network

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.ui.screenmodels.OverViewScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.OverviewsScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.UserScreenModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class CourseDataSourceImpl : CourseDataSource {
    val overviewList = BehaviorSubject.create<List<Overview>>()

    val faqList = BehaviorSubject.create<List<Faq>>()

    val userSubject = BehaviorSubject.create<User>()

    override val user: MutableLiveData<User> = MutableLiveData()

    override fun setOverviews(overview: List<Overview>) {
        overviewList.onNext(overview)
    }

    override fun observeOverviews(): BehaviorSubject<List<Overview>> {
        return overviewList
    }


    override fun setFaq(faq: List<Faq>) {
        faqList.onNext(faq)
    }

    override fun observeFaqs(): BehaviorSubject<List<Faq>> {
        return faqList
    }

    override fun setUserName(user: User) {
        userSubject.onNext(user)
    }

    override fun observeUser(): BehaviorSubject<User> {
        return userSubject
    }

    override fun getDataUser() {
        val cd = CompositeDisposable()
        cd.add(
            this.observeUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ onGetUser(it) }, {})
        )
    }


    private fun onGetUser(userI: User) {
        user.postValue(userI)
    }
}