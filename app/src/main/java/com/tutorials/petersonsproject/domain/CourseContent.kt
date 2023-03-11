package com.tutorials.petersonsproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseContent(
    val title: String
) : Parcelable