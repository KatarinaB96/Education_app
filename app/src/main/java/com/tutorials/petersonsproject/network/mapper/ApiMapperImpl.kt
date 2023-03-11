package com.tutorials.petersonsproject.network.mapper

import com.tutorials.petersonsproject.domain.CourseCategory
import com.tutorials.petersonsproject.domain.Exam
import com.tutorials.petersonsproject.domain.Faq
import com.tutorials.petersonsproject.domain.Overview
import com.tutorials.petersonsproject.domain.hierarchy.Module
import com.tutorials.petersonsproject.domain.hierarchy.TopicContent
import com.tutorials.petersonsproject.network.CourseCategoryResponse
import com.tutorials.petersonsproject.network.hierarchy.ContentHierarchyResponse
import com.tutorials.petersonsproject.network.hierarchy.CourseHierarchiesResponse

class ApiMapperImpl : ApiMapper {


    override fun mapToCourse(courseCategoryResponse: List<CourseCategoryResponse>): List<CourseCategory> {
        return courseCategoryResponse.map { coursecategory ->
            CourseCategory(
                coursecategory.id,
                coursecategory.name,
                coursecategory.courses.map {
                    Exam(
                        coursecategory.id,
                        it.courseMeta.brightspaceId,
                        it.courseMeta.imageUrl,
                        it.courseMeta.title,
                        it.courseMeta.overview.map { Overview(it.image, it.description) },
                        it.courseMeta.faq.map { Faq(it.heading, it.body) })
                })
        }
    }

    override fun mapToContent(courseHierarchyResponses: List<CourseHierarchiesResponse>): List<Module> {

        val response = courseHierarchyResponses.firstOrNull()?.hierarchy

        return response?.map {
            Module(
                it.hierarchyId,
                it.hierarchyTitle,
                mapToTopic(it.contentResponse ?: emptyList())
            )
        } ?: emptyList()
    }



    private fun mapToTopic(hierarchyResponse: List<ContentHierarchyResponse>): List<TopicContent> {

        return hierarchyResponse.map { contentHierarchyResponse ->
            TopicContent(
                contentHierarchyResponse.contentId,
                contentHierarchyResponse.topicType,
                contentHierarchyResponse.type,
                contentHierarchyResponse.url ?: "",
                contentHierarchyResponse.title,
                contentHierarchyResponse.toolItemId,
                contentHierarchyResponse.gradeItemId,
                mapToTopic(contentHierarchyResponse.hierarchyContent ?: mutableListOf())
            )
        }
    }

}