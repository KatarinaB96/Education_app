package com.tutorials.petersonsproject.domain.publishSubject

import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.domain.hierarchy.TopicContent
import io.reactivex.subjects.BehaviorSubject

interface TopicDataSource {
    fun setTopics(list: List<TopicContent>)
    fun observeTopics(): BehaviorSubject<List<TopicContent>>
}