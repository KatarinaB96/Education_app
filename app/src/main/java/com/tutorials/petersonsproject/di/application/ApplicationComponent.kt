package com.tutorials.petersonsproject.di.application


import com.tutorials.petersonsproject.base.TemplateApplication
import com.tutorials.petersonsproject.di.application.module.ApplicationModule
import com.tutorials.petersonsproject.di.application.module.DataModule

import com.tutorials.petersonsproject.di.application.module.MapperModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        DataModule::class,
        MapperModule::class
    ]
)
interface ApplicationComponent : ApplicationComponentInjects, ApplicationComponentExposes {
    object Initializer {
        fun init(templateApplication: TemplateApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(templateApplication))
                .dataModule(DataModule())
                .mapperModule(MapperModule())
                .build()
    }
}