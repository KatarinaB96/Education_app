package com.tutorials.petersonsproject.domain.hierarchy

data class Module(
    val id: Long,
    val title: String,
    val topics: List<TopicContent>
)