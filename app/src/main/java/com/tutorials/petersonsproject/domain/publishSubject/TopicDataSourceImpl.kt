package com.tutorials.petersonsproject.domain.publishSubject

import com.tutorials.petersonsproject.domain.hierarchy.TopicContent
import io.reactivex.subjects.BehaviorSubject


class TopicDataSourceImpl : TopicDataSource {
    private val topicList = BehaviorSubject.create<List<TopicContent>>()


    override fun setTopics(list: List<TopicContent>) {
        topicList.onNext(list)
    }

    override fun observeTopics(): BehaviorSubject<List<TopicContent>> {
        return topicList
    }


}