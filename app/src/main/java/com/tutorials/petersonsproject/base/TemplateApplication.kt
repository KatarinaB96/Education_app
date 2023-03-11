package com.tutorials.petersonsproject.base

import android.app.Application
import android.content.Context
import com.tutorials.petersonsproject.ComponentFactory
import com.tutorials.petersonsproject.di.application.ApplicationComponent


class TemplateApplication : Application() {
    private lateinit var applicationComponent: ApplicationComponent


    companion object {
        fun from(context: Context): TemplateApplication {
            return context.applicationContext as TemplateApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = ComponentFactory.createApplicationComponent(this)
        applicationComponent.inject(this)
    }

    fun getApplicationComponent() = applicationComponent

}