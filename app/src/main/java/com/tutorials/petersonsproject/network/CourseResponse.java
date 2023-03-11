package com.tutorials.petersonsproject.network;

import com.squareup.moshi.Json;

import java.util.Date;

import moe.banana.jsonapi2.HasOne;
import moe.banana.jsonapi2.JsonApi;
import moe.banana.jsonapi2.Resource;

@JsonApi(type = "courses")
public final class CourseResponse extends Resource {

    @Json(name = "order")
    private int order;

    @Json(name = "platform")
    private int platform;

    @Json(name = "is_free")
    private boolean isFree;

    @Json(name = "created_at")
    private Date createdAt;

    @Json(name = "updated_at")
    private Date updatedAt;

    @Json(name = "course-meta")
    private HasOne<CourseMeta> courseMetas;

    public boolean isFree() {
        return isFree;
    }

    public int getOrder() {
        return order;
    }

    public int getPlatform() {
        return platform;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public CourseMeta getCourseMeta() {
        if (courseMetas != null) {
            return courseMetas.get(getContext());
        } else {
            return null;
        }
    }
}