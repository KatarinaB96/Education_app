package com.tutorials.petersonsproject.network

import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.hierarchy.D2lSecureSessionVal
import com.tutorials.petersonsproject.domain.hierarchy.D2lSessionVal
import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.domain.user.Attributes
import com.tutorials.petersonsproject.domain.user.User
import com.tutorials.petersonsproject.network.hierarchy.SessionCookieResponseApi
import com.tutorials.petersonsproject.network.mapper.ApiMapper
import com.tutorials.petersonsproject.sharedpreferences.DeviceSharedPreferences
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class CourseCategoryClientImpl(
    private val apiMapper: ApiMapper,
    private val networkService: NetworkService,
    private val sharedPreferences: DeviceSharedPreferences,
    private val sessionCookieRequestApi: SessionCookieRequestApi
) : CourseCategoryClient {
    override fun getCourseCategory(): Observable<List<CourseCategory>> {
        return networkService.getCourseCategories("DTC", sharedPreferences.getAccessToken())
            .map { apiMapper.mapToCourse(it.toList()) }
    }

    override fun getContent(id: Long): Single<List<Module>> {
        return networkService.getCourseContent(id, sharedPreferences.getAccessToken())
            .map { apiMapper.mapToContent(it.toList()) }
    }

    override fun getCookies(): Single<SessionCookie> {
        return networkService.getCookies(
            sessionCookieRequestApi,
            sharedPreferences.getAccessToken()
        ).map {
            SessionCookie(
                it.id,
                it.type,
                (D2lSessionVal(
                    it.d2lSessionVal.name,
                    it.d2lSessionVal.domain,
                    it.d2lSessionVal.value,
                    it.d2lSessionVal.expires
                )),
                D2lSecureSessionVal(
                    it.d2lSecureSessionVal.name,
                    it.d2lSecureSessionVal.domain,
                    it.d2lSecureSessionVal.value,
                    it.d2lSecureSessionVal.expires
                )
            )
        }
    }

    override fun getUser(): Single<User> {
        return networkService.getUser(sharedPreferences.getAccessToken()).map {
            User(
                it.userEmail,
                it.userFirstName,
                it.userLastName
            )
        }
    }

    override fun authenticate(): Completable {
        return networkService.authenticate(sharedPreferences.getAccessToken())
    }


}