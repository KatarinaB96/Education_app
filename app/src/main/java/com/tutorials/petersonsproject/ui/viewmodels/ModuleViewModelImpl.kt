package com.tutorials.petersonsproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.base.BaseViewModel
import com.tutorials.petersonsproject.domain.*
import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.domain.hierarchy.SessionCookie
import com.tutorials.petersonsproject.domain.hierarchy.TopicContent
import com.tutorials.petersonsproject.domain.publishSubject.TopicDataSource
import com.tutorials.petersonsproject.ui.screenmodels.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ModuleViewModelImpl(
    private val repository: CourseRepository,
    private val topicDataSource: TopicDataSource
) : ModuleContentViewModel,
    BaseViewModel() {

    override val modules: MutableLiveData<ModulesContentScreenModel> = MutableLiveData()

    val list: MutableList<Module> = mutableListOf()



    override fun getDataAboutHierarchyContent(id: Long) {
        addDisposable(
            repository.getHierarchies(id)
                .doOnSuccess { list.addAll(it) }
                .map {
                    ModulesContentScreenModel(it.map {
                        ModuleContentScreenModel(it.id, it.title)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ onGetCourseSuccess(it) }, { it.printStackTrace() })
        )
    }


    override fun setTopicData(moduleID: Long) {
        val topic = list.find { it.id == moduleID }?.topics?.map {
            TopicContent(
                it.id,
                it.topicType,
                it.type,
                it.url,
                it.title,
                it.toolItemId,
                it.gradeItemId,
                it.contents
            )
        } ?: emptyList()

        topicDataSource.setTopics(topic)

    }


    private fun onGetCourseSuccess(hierarchyContentScreenModel: ModulesContentScreenModel) {
        modules.postValue(hierarchyContentScreenModel)
    }

}