package com.tutorials.petersonsproject.ui.screenmodels

import com.tutorials.petersonsproject.domain.Exam

data class CourseScreenModel(
    val id:String,
    val title: String,
    val exam: List<Exam>
)