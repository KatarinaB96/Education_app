package com.tutorials.petersonsproject.domain.publishSubject

import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.domain.Overview
import io.reactivex.subjects.BehaviorSubject


class CourseDataSourceImpl  {
    val overviewList = BehaviorSubject.create<List<Overview>>()

    val faqList = BehaviorSubject.create<List<Faq>>()

     fun setOverviews(overview: List<Overview>) {
        overviewList.onNext(overview)
    }

     fun observeOverviews(): BehaviorSubject<List<Overview>> {
        return overviewList
    }


     fun setFaq(faq: List<Faq>) {
        faqList.onNext(faq)
    }

     fun observeFaqs(): BehaviorSubject<List<Faq>> {
        return faqList
    }



}