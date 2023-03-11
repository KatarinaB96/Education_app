package com.tutorials.petersonsproject.network;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

import moe.banana.jsonapi2.HasMany;
import moe.banana.jsonapi2.JsonApi;
import moe.banana.jsonapi2.Resource;

@JsonApi(type = "course-categories")
public final class CourseCategoryResponse extends Resource {

    @Json(name = "name")
    private String name;

    @Json(name = "order")
    private int order;

    @Json(name = "created_at")
    private Date createdAt;

    @Json(name = "updated_at")
    private Date updatedAt;

    @Json(name = "courses")
    private HasMany<CourseResponse> courses;

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<CourseResponse> getCourses() {
        return courses.get(getContext());
    }
}