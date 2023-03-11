package com.tutorials.petersonsproject.di.fragment


import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.di.activity.ActivityComponent

import com.tutorials.petersonsproject.di.fragment.module.FragmentMapperModule
import com.tutorials.petersonsproject.di.fragment.module.FragmentViewModelModule
import com.tutorials.petersonsproject.di.scopes.FragmentScope
import com.tutorials.petersonsproject.di.fragment.module.FragmentModule

import dagger.Component

@FragmentScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [
        FragmentModule::class,
        FragmentViewModelModule::class,
        FragmentMapperModule::class
    ]
)


interface FragmentComponent : FragmentComponentInjects, FragmentComponentExposes {
    object Initializer {
        fun init(
//            baseActivity: BaseActivity,
            baseFragment: BaseFragment,
            activityComponent: ActivityComponent
        ): FragmentComponent =
            DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .fragmentModule(FragmentModule(baseFragment))
//                .fragmentMapperModule(FragmentMapperModule())
                .fragmentViewModelModule(FragmentViewModelModule(baseFragment))
                .build()
    }


}