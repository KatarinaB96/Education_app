package com.tutorials.petersonsproject.di.application

import com.tutorials.petersonsproject.base.TemplateApplication


interface ApplicationComponentInjects {
    fun inject(application: TemplateApplication)
}