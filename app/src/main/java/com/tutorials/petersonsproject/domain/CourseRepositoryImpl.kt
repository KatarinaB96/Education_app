package com.tutorials.petersonsproject.domain

import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.network.CourseCategoryClient
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class CourseRepositoryImpl(
    private val categoryClient: CourseCategoryClient
) : CourseRepository {

    override fun getCourseCategory(): Observable<List<CourseCategory>> {
        return categoryClient.getCourseCategory()
    }

    override fun getHierarchies(id: Long): Single<List<Module>> {
        return categoryClient.getContent(id)
    }

    override fun getCookies(): Single<SessionCookie> {
        return categoryClient.getCookies()
    }

    override fun authenticate(): Completable {
        return categoryClient.authenticate()
    }

    override fun getUser(): Single<User> {
        return categoryClient.getUser()
    }

}