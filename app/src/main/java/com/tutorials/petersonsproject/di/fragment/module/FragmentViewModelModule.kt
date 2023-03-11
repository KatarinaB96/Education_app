package com.tutorials.petersonsproject.di.fragment.module

import androidx.lifecycle.ViewModelProvider

import com.tutorials.petersonsproject.base.BaseFragment
import com.tutorials.petersonsproject.domain.CourseRepository
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.domain.publishSubject.TopicDataSource
import com.tutorials.petersonsproject.ui.ViewModelProviderFactory
import com.tutorials.petersonsproject.ui.viewmodels.*

import dagger.Module
import dagger.Provides

@Module
class FragmentViewModelModule(private val fragment: BaseFragment) {

    @Provides
    internal fun provideViewModelCourseCategory(
        courseRepository: CourseRepository,
        courseDataSource: CourseDataSource
    ): CourseViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(CourseViewModelImpl::class) {
            CourseViewModelImpl(courseRepository, courseDataSource)
        })[CourseViewModelImpl::class.java]
    }

    @Provides
    internal fun provideViewModelOverviews(courseDataSource: CourseDataSource):
            OverviewViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(OverviewViewModelImpl::class) {
            OverviewViewModelImpl(courseDataSource)
        })[OverviewViewModelImpl::class.java]
    }

    @Provides
    internal fun provideViewModelFaqs(courseDataSource: CourseDataSource):
            FaqViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(FaqViewModelImpl::class) {
            FaqViewModelImpl(courseDataSource)
        })[FaqViewModelImpl::class.java]
    }

    @Provides
    internal fun provideModule(repository: CourseRepository, topicDataSource: TopicDataSource):
            ModuleContentViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(ModuleViewModelImpl::class) {
            ModuleViewModelImpl(repository, topicDataSource)
        })[ModuleViewModelImpl::class.java]
    }

    @Provides
    internal fun provideViewModelTopics(
        topicDataSource: TopicDataSource,
        repository: CourseRepository
    ):
            TopicViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(TopicViewModelImpl::class) {
            TopicViewModelImpl(topicDataSource, repository)
        })[TopicViewModelImpl::class.java]
    }

    @Provides
    internal fun provideViewModel(
        repository: CourseRepository
    ):
            CookieViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(CookieViewModelImp::class) {
            CookieViewModelImp( repository)
        })[CookieViewModelImp::class.java]
    }


//    @Provides
//    internal fun provideViewModelAnimalWithBreeds(repository: AnimalRepository,mapper: AnimalsWithBreedsMapper): AnimalWithBreedsViewModel {
//        return ViewModelProvider(activity, ViewModelProviderFactory(AnimalWithBreedsViewModelImpl::class) {
//            AnimalWithBreedsViewModelImpl(repository,mapper)
//        })[AnimalWithBreedsViewModelImpl::class.java]
//    }

//    @Provides
//    internal fun provideViewModelBreeds(repository: AnimalRepository): BreedViewModel {
//        return ViewModelProvider(fragment, ViewModelProviderFactory(BreedViewModelImpl::class) {
//            BreedViewModelImpl(repository)
//        })[BreedViewModelImpl::class.java]
//    }


    interface Exposes {
        fun provideViewModelCourseCategory(): CourseViewModel
        fun provideViewModelOverviews(): OverviewViewModel
        fun provideViewModelFaqs(): FaqViewModel
        fun provideModule(): ModuleContentViewModel
        fun provideViewModelTopics(): TopicViewModel
        fun provideViewModel():CookieViewModel
    }

}