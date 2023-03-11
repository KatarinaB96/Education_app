package com.tutorials.petersonsproject.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Overview(
    val image: String,
    val description: String
) : Parcelable
