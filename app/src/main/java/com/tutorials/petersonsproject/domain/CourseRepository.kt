package com.tutorials.petersonsproject.domain

import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.network.hierarchy.SessionCookieResponseApi
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CourseRepository {
    fun getCourseCategory(): Observable<List<CourseCategory>>

    fun getHierarchies(id:Long): Single<List<Module>>

    fun getCookies(): Single<SessionCookie>

    fun authenticate(): Completable

    fun getUser(): Single<User>

}