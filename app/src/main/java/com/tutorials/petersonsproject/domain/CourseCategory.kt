package com.tutorials.petersonsproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseCategory(
    val id: String,
    val title: String,
    val exam: List<Exam>
) : Parcelable