package com.tutorials.petersonsproject.di.activity.module


import com.tutorials.petersonsproject.base.BaseActivity
import dagger.Module

@Module
class ActivityViewModelModule(private val baseActivity: BaseActivity) {

//    @Provides
//    internal fun provideViewModel(repository: AnimalRepository): AnimalViewModel {
//        return ViewModelProvider(
//            baseActivity,
//            ViewModelProviderFactory(AnimalViewModelImpl::class) {
//                AnimalViewModelImpl(repository)
//            })[AnimalViewModelImpl::class.java]
//    }
//
//    interface Exposes {
//        fun animalViewModel(): AnimalViewModel
//    }

}