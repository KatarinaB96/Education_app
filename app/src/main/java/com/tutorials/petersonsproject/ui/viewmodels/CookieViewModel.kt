package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie

interface CookieViewModel {
    val cookies: MutableLiveData<SessionCookie>
    fun getCookies()



}