package com.tutorials.petersonsproject.network

import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.network.hierarchy.SessionCookieResponseApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CourseCategoryClient {
    fun getCourseCategory():  Observable<List<CourseCategory>>
    fun getContent(id:Long): Single<List<Module>>
    fun getCookies(): Single<SessionCookie>

    fun authenticate(): Completable
    fun getUser(): Single<User>

}