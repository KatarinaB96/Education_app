package com.tutorials.petersonsproject.network

import com.tutorials.petersonsproject.network.hierarchy.CourseHierarchiesResponse
import com.tutorials.petersonsproject.network.hierarchy.SessionCookieResponseApi
import com.tutorials.petersonsproject.network.user.UserResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*


interface NetworkService {

    @Headers(
        "X-CLIENT: DTC",
        "Content-Type: application/vnd.api+json",
        "Accept: application/vnd.api+json"
    )

    @GET("/api/v1/course-categories?include=courses,courses.course-meta")
    fun getCourseCategories(
        @Query("filter[client]") appType: String,
        @Header("Authorization") authHeader: String
    ): Observable<Array<CourseCategoryResponse>>


    @Headers(
        "X-CLIENT: DTC",
        "Content-Type: application/vnd.api+json",
        "Accept: application/vnd.api+json"
    )
    @GET("api/v1/course-hierarchies")
    fun getCourseContent(
        @Query("filter[org_unit_id]") courseId: Long,
        @Header("Authorization") authHeader: String
    ): Single<Array<CourseHierarchiesResponse>>


    @Headers(
        "X-CLIENT: DTC",
        "Content-Type: application/vnd.api+json",
        "Accept: application/vnd.api+json"
    )
    @POST("api/v1/session-cookies")
    fun getCookies(
        @Body sessionCookieRequestApi: SessionCookieRequestApi,
        @Header("Authorization") authHeader: String
    ): Single<SessionCookieResponseApi>


    @Headers(
        "X-CLIENT: DTC",
        "Content-Type: application/vnd.api+json",
        "Accept: application/vnd.api+json"
    )
    @GET("/api/authenticate-d2l-user")
    fun authenticate(
        @Header("Authorization") authHeader: String
    ): Completable


    @Headers(
        "X-CLIENT: DTC",
        "Content-Type: application/vnd.api+json",
        "Accept: application/vnd.api+json"
    )
    @GET("api/v1/users/me")
    fun getUser(
        @Header("Authorization") authHeader: String
    ):Single<UserResponse>



}