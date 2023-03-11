package com.tutorials.petersonsproject.di.application.module

import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.tutorials.petersonsproject.domain.CourseRepository
import com.tutorials.petersonsproject.domain.CourseRepositoryImpl
import com.tutorials.petersonsproject.network.SessionCookieRequestApi
import com.tutorials.petersonsproject.network.*
import com.tutorials.petersonsproject.network.hierarchy.CourseHierarchiesResponse
import com.tutorials.petersonsproject.network.mapper.ApiMapper
import com.tutorials.petersonsproject.network.user.UserResponse
import com.tutorials.petersonsproject.sharedpreferences.DeviceSharedPreferences
import dagger.Module
import dagger.Provides
import moe.banana.jsonapi2.ResourceAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }


    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl("https://mmw.petersons.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JsonApiConverterFactory.create(moshi))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        val adapterFactory: JsonAdapter.Factory = ResourceAdapterFactory.builder()
            .add(CourseCategoryResponse::class.java)
//            .add(SessionCookieResponseApi::class.java)
            .add(CourseResponse::class.java)
            .add(CourseMeta::class.java)
//            .add(Passage::class.java)
            .add(CourseHierarchiesResponse::class.java)
            .add(UserResponse::class.java)
            .add(Unknown::class.java)
            .build()

        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(adapterFactory)
            .build()
    }


    @Provides
    @Singleton
    internal fun provideNetworkService(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    internal fun provideApi(): SessionCookieRequestApi =
        SessionCookieRequestApi()


    @Provides
    @Singleton
    internal fun provideClient(
        networkService: NetworkService,
        apiMapper: ApiMapper,
        sharedPreferences: DeviceSharedPreferences,
        sessionCookieRequestApi: SessionCookieRequestApi
    ): CourseCategoryClient {
        return CourseCategoryClientImpl(apiMapper, networkService, sharedPreferences,sessionCookieRequestApi )
    }


    @Provides
    @Singleton
    internal fun provideRepository(
        courseCategoryClient: CourseCategoryClient
    ): CourseRepository {
        return CourseRepositoryImpl(courseCategoryClient)
    }

    interface Exposes {
        fun provideMoshi(): Moshi
        fun provideClient(): CourseCategoryClient
        fun provideRepository(): CourseRepository
        fun provideApi(): SessionCookieRequestApi
    }

}