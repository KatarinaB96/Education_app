package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.base.BaseViewModel
import com.tutorials.petersonsproject.domain.publishSubject.CourseDataSource
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.ui.screenmodels.OverViewScreenModel
import com.tutorials.petersonsproject.ui.screenmodels.OverviewsScreenModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OverviewViewModelImpl(

    private val courseDataSourceImpl: CourseDataSource

) : OverviewViewModel,
    BaseViewModel() {


    override val overview: MutableLiveData<OverviewsScreenModel> = MutableLiveData()

    override fun getDataAboutOverviews() {
        addDisposable(
            courseDataSourceImpl.observeOverviews()
                .map(this::mapToScreenModel)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe({ onGetOverviewSuccess(it) }, {})
        )
    }

    private fun mapToScreenModel(overviews: List<Overview>): OverviewsScreenModel {
        return OverviewsScreenModel(overviews.map {
            OverViewScreenModel(
                it.image,
                it.description
            )
        })
    }

    private fun onGetOverviewSuccess(overviewsScreenModel: OverviewsScreenModel) {
        overview.postValue(overviewsScreenModel)
    }

}