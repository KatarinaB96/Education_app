package com.tutorials.petersonsproject.di.activity



import com.tutorials.petersonsproject.base.BaseActivity
import com.tutorials.petersonsproject.di.application.ApplicationComponent
import com.tutorials.petersonsproject.di.scopes.ActivityScope
import com.tutorials.petersonsproject.di.activity.module.ActivityModule
import com.tutorials.petersonsproject.router.module.RouterModule
import dagger.Component


@ActivityScope
@Component(
    dependencies = [(ApplicationComponent::class)],
    modules = [
        ActivityModule::class,
        RouterModule::class
    ]
)

interface ActivityComponent : ActivityComponentInjects, ActivityComponentExposes {

    object Initializer {
        fun init(
            baseActivity: BaseActivity,
            applicationComponent: ApplicationComponent
        ): ActivityComponent =
            DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(baseActivity))
                .routerModule(RouterModule(baseActivity))
//                .activityViewModelModule(ActivityViewModelModule(baseActivity))
                .build()
    }
}