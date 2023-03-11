package com.tutorials.petersonsproject.ui.screenmodels

data class LessonScreenModel(
    val id: Long,
    val url: String,
    val title: String,
    val toolItemId: Int,
    val gradeItemId: Int
)