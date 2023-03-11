package com.tutorials.petersonsproject.network.mapper

import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.network.CourseCategoryResponse
import com.tutorials.petersonsproject.network.hierarchy.CourseHierarchiesResponse

interface ApiMapper {
    fun mapToCourse(courseCategoryResponse: List<CourseCategoryResponse>): List<CourseCategory>
    fun mapToContent(courseHierarchyResponses: List<CourseHierarchiesResponse>): List<Module>?

}