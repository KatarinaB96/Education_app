package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.base.BaseViewModel
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.ui.screenmodels.FaqScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.FaqsScreenModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FaqViewModelImpl(

    private val courseDataSourceImpl: CourseDataSource,

    ) : FaqViewModel,
    BaseViewModel() {
    override val faq: MutableLiveData<FaqsScreenModel> = MutableLiveData()


    override fun getDataAboutFaqs() {
        addDisposable(
            courseDataSourceImpl.observeFaqs()
                .map(this::mapToScreenModel)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe({ onGetFaqSuccess(it) }, {})
        )
    }


    private fun mapToScreenModel(faq: List<Faq>): FaqsScreenModel {
        return FaqsScreenModel(faq.map {
            FaqScreenModel(
                it.question,
                it.answer
            )
        })
    }

    private fun onGetFaqSuccess(faqsScreenModel: FaqsScreenModel) {
        faq.postValue(faqsScreenModel)
    }

}