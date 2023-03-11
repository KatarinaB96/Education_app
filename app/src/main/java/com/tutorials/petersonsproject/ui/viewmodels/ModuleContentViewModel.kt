package com.tutorials.petersonsproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tutorials.petersonsproject.ui.screenmodels.ModulesContentScreenModel

interface ModuleContentViewModel {
    val modules: MutableLiveData<ModulesContentScreenModel>
    fun getDataAboutHierarchyContent(id: Long)

    fun setTopicData(moduleID: Long)




}