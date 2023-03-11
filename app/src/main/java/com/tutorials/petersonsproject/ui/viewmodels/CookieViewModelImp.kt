package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.base.BaseViewModel
import com.tutorials.petersonsproject.domain.CourseRepository
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CookieViewModelImp(private val repository: CourseRepository) : CookieViewModel,
    BaseViewModel() {

    override val cookies: MutableLiveData<SessionCookie> = MutableLiveData()


    override fun getCookies() {
        addDisposable(
            repository.getCookies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ getCookie(it) }, { it.printStackTrace() })
        )
    }


    private fun getCookie(cookie: SessionCookie) {
        cookies.postValue(cookie)
    }


}