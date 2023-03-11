package com.tutorials.petersonsproject.di.activity.module

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.di.scopes.ActivityScope
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.domain.publishSubject.TopicDataSource
import com.tutorials.petersonsproject.domain.publishSubject.TopicDataSourceImpl
import com.tutorials.petersonsproject.network.CourseDataSourceImpl
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    internal fun provideActivity(): Activity {
        return baseActivity
    }

    @Provides
    internal fun provideFragmentManager(): FragmentManager {
        return baseActivity.supportFragmentManager
    }


    @Provides
    @ActivityScope
    internal fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    @ActivityScope
    internal fun provideObservableOverviews(): CourseDataSource {
        return CourseDataSourceImpl()
    }

    @Provides
    @ActivityScope
    internal fun provideObservableTopics(): TopicDataSource {
        return TopicDataSourceImpl()
    }

    //this is for navigation

//    @Provides
//    @ActivityScope
//    internal fun provideRouter(fragmentManager: FragmentManager): Router {
//        return RouterImpl(daggerActivity, fragmentManager)
//    }


    interface Exposes {

        fun activity(): Activity

        fun context(): Context

        fun fragmentManager(): FragmentManager

        fun provideObservableOverviews(): CourseDataSource

        fun provideObservableTopics(): TopicDataSource

    }
}