package com.tutorials.petersonsproject.domain.hierarchy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopicContent(
    val id: Long,
    val topicType: Int,
    val type:Int,
    val url: String,
    val title: String,
    val toolItemId: Int,
    val gradeItemId: Int,
    val contents: List<TopicContent>
):Parcelable
