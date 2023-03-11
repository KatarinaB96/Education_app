package com.tutorials.petersonsproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exam(
    val categoryId: String,
    val id: Long,
    val image: String,
    val title: String,
    val overview: List<Overview>,
    val faq: List<Faq>
) : Parcelable