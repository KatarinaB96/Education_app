package com.tutorials.petersonsproject

import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.base.TemplateApplication
import com.tutorials.petersonsproject.di.activity.ActivityComponent
import com.tutorials.petersonsproject.di.application.ApplicationComponent
import com.tutorials.petersonsproject.di.fragment.FragmentComponent


object ComponentFactory {
    fun createApplicationComponent(templateApplication: TemplateApplication): ApplicationComponent {
        return ApplicationComponent.Initializer.init(templateApplication)
    }

    fun createActivityComponent(baseActivity: BaseActivity, applicationComponent: ApplicationComponent): ActivityComponent {
        return ActivityComponent.Initializer.init(baseActivity, applicationComponent)
    }

    fun createFragmentComponent( baseFragment: BaseFragment, activityComponent: ActivityComponent): FragmentComponent {
        return FragmentComponent.Initializer.init(baseFragment,activityComponent)
    }
}