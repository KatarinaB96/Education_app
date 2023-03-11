package com.tutorials.petersonsproject.ui.screenmodels

data class TopicScreenModel(
    val id: Long,
    val topicType: Int,
    val type: Int,
    val url: String,
    val title: String,
    val toolItemId: Int,
    val gradeItemId: Int,
    val contents: List<TopicScreenModel>
)