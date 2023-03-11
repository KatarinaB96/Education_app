package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.ui.screenmodels.*

interface FaqViewModel {
    val faq: MutableLiveData<FaqsScreenModel>
    fun getDataAboutFaqs()

}