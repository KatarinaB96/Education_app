package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.base.BaseViewModel
import com.tutorials.petersonsproject.domain.*
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.domain.hierarchy.TopicContent
import com.tutorials.petersonsproject.domain.publishSubject.TopicDataSource
import com.tutorials.petersonsproject.network.hierarchy.ContentHierarchyResponse
import com.tutorials.petersonsproject.ui.screenmodels.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopicViewModelImpl(
    private val topicDataSource: TopicDataSource,
    private val repository: CourseRepository
) : TopicViewModel,
    BaseViewModel() {

    override val topicContent: MutableLiveData<TopicsScreenModel> = MutableLiveData()
//    override val cookies: MutableLiveData<SessionCookie> = MutableLiveData()

    override fun getDataAboutTopics() {
        addDisposable(
            topicDataSource.observeTopics()
                .map(this::mapToScreenModel)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe({ onGetOverviewSuccess(it) }, {})
        )
    }




    private fun mapToScreenModel(topics: List<TopicContent>): TopicsScreenModel {
        return TopicsScreenModel(topics.map {
            TopicScreenModel(
                it.id,
                it.topicType,
                it.type,
                it.url,
                it.title,
                it.toolItemId,
                it.gradeItemId,
                mapToTopic(it.contents)
            )
        })
    }

    private fun mapToTopic(topicContent: List<TopicContent>): List<TopicScreenModel> {
        return topicContent.map {
            TopicScreenModel(
                it.id,
                it.topicType,
                it.type,
                it.url,
                it.title,
                it.toolItemId,
                it.gradeItemId,
                mapToTopic(it.contents)
            )
        }
    }


    private fun onGetOverviewSuccess(screenModel: TopicsScreenModel) {
        topicContent.postValue(screenModel)
    }

}