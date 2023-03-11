package com.tutorials.petersonsproject.network;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

import moe.banana.jsonapi2.JsonApi;
import moe.banana.jsonapi2.Resource;

@JsonApi(type = "course-metas")
public final class CourseMeta extends Resource {

    @Json(name = "brightspace_id")
    private long brightspaceId;

    @Json(name = "title")
    private String title;

    @Json(name = "subtitle")
    private String subtitle;

    @Json(name = "thumbnail_url")
    private String thumbnailUrl;

    @Json(name = "image_url")
    private String imageUrl;

    @Json(name = "overview")
    private List<Overview> overview;

    @Json(name = "faq")
    private List<FaqQuestion> faq;

    @Json(name = "is_online")
    private boolean isOnline;

    @Json(name = "created_at")
    private Date createdAt;

    @Json(name = "updated_at")
    private Date updatedAt;

    public long getBrightspaceId() {
        return brightspaceId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Overview> getOverview() {
        return overview;
    }

    public List<FaqQuestion> getFaq() {
        return faq;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}