package com.tutorials.petersonsproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Faq(
    val question: String,
    val answer: String
) : Parcelable
